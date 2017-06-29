/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml, oolong
 * Author  :
 * 	  Samual Alexander, springml
 * Contributors:
 *    Kagan Turgut, oolong
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;

import com.springml.salesforce.wave.api.ForceAPI;
import com.springml.salesforce.wave.model.AddTaskRequest;
import com.springml.salesforce.wave.model.AddTaskResponse;
import com.springml.salesforce.wave.model.ForceResponse;
import com.springml.salesforce.wave.model.SOQLResult;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Default implementation for Salesforce REST API calls
 */
public class ForceAPIImpl extends AbstractAPIImpl implements ForceAPI {
    private static final Logger LOG = Logger.getLogger(ForceAPIImpl.class);

    public ForceAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public SOQLResult query(String soql) throws Exception {
        StringBuilder queryParam = new StringBuilder();
        queryParam.append(QUERY_PARAM);
        queryParam.append(soql);
        SFConfig sfConfig = getSfConfig();
        String queryPath = getQueryPath(sfConfig);
        URI queryURI = getSfConfig().getRequestURI(
                getSfConfig().getPartnerConnection(), queryPath, queryParam.toString());

        return query(queryURI, 1);
    }

    public SOQLResult queryMore(SOQLResult soqlResult) throws Exception {
        if (soqlResult.isDone()) {
            throw new Exception("Already all records are read");
        }

        URI requestURI = getSfConfig().getRequestURI(getSfConfig().getPartnerConnection(), soqlResult.getNextRecordsUrl());
        return query(requestURI, 1);
    }

    public AddTaskResponse addTask(AddTaskRequest addTask) throws Exception {
        SFConfig sfConfig = getSfConfig();
        String taskPath = getTaskPath(sfConfig);
        URI taskURI = sfConfig.getRequestURI(
                sfConfig.getPartnerConnection(), taskPath);

        String request = getObjectMapper().writeValueAsString(addTask);
        String responseStr = getHttpHelper().post(taskURI, getSfConfig().getSessionId(), request);

        AddTaskResponse response = null;
        try {
            response = getObjectMapper().readValue(responseStr.getBytes(), AddTaskResponse.class);
        } catch (IOException e) {
            response = new AddTaskResponse();
            response.setError(responseStr);
            response.setSuccess(false);
        }

        return response;
    }

    public ForceResponse insertObject(String object, String jsonContent) throws Exception {
        SFConfig sfConfig = getSfConfig();
        String insertPath = getInsertPath(sfConfig, object);
        URI taskURI = sfConfig.getRequestURI(
                sfConfig.getPartnerConnection(), insertPath);

        String responseStr = getHttpHelper().post(taskURI, getSfConfig().getSessionId(), jsonContent);

        ForceResponse response = null;
        try {
            response = getObjectMapper().readValue(responseStr.getBytes(), ForceResponse.class);
        } catch (IOException e) {
            response = new ForceResponse();
            response.setError(responseStr);
            response.setSuccess(false);
        }

        return response;
    }


    public String getSFEndpoint() throws Exception {
        URI seURI = new URI(getSfConfig().getPartnerConnection().getConfig().getServiceEndpoint());
        return new URI(seURI.getScheme(),seURI.getUserInfo(), seURI.getHost(), seURI.getPort(),
                null, null, null).toString();
    }

    private String getInsertPath(SFConfig sfConfig, String object) {
        StringBuilder objPath = new StringBuilder();
        objPath.append(SERVICE_PATH);
        objPath.append("v");
        objPath.append(sfConfig.getApiVersion());
        objPath.append(PATH_SOBJECTS);
        objPath.append(object);

        return objPath.toString();
    }

    private String getTaskPath(SFConfig sfConfig) {
        StringBuilder taskPath = new StringBuilder();
        taskPath.append(SERVICE_PATH);
        taskPath.append("v");
        taskPath.append(sfConfig.getApiVersion());
        taskPath.append(PATH_TASK);

        return taskPath.toString();
    }

    private SOQLResult query(URI queryURI, int attempt) throws Exception {
        SOQLResult soqlResult = null;
        try {
            String response = getHttpHelper().get(queryURI,
                    getSfConfig().getSessionId(getSfConfig().getPartnerConnection()), getSfConfig().getBatchSize());

            LOG.debug("Query Response from server " + response);
            soqlResult = getObjectMapper().readValue(response.getBytes(), SOQLResult.class);
        } catch (Exception e) {
            LOG.warn("Error while executing salesforce query ", e);
            if (e.getMessage().contains("QUERY_TIMEOUT") && attempt < 5) {
                LOG.info("Retrying salesforce query");
                LOG.info("Retry attempt " + attempt);
                //Retrying incase of Salesforce service timeout
                soqlResult = query(queryURI, ++attempt);
            } else if (e.getMessage().contains("INVALID_SESSION_ID") && attempt < 5) {
                getSfConfig().closeConnection();
                LOG.info("Retrying with new connection...");
                soqlResult = query(queryURI, ++attempt);
            } else {
                throw e;
            }
        }

        return soqlResult;
    }

    private String getQueryPath(SFConfig sfConfig) {
        StringBuilder queryPath = new StringBuilder();
        queryPath.append(SERVICE_PATH);
        queryPath.append("v");
        queryPath.append(sfConfig.getApiVersion());
        queryPath.append(PATH_QUERY);

        return queryPath.toString();
    }

}

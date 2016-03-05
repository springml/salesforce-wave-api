package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;

import org.apache.log4j.Logger;

import com.springml.salesforce.wave.api.ForceAPI;
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
                attempt++;
                soqlResult = query(queryURI, attempt);
            } else {
                throw e;
            }
        } finally {
            if (getSfConfig().getPartnerConnection() != null && soqlResult != null && soqlResult.isDone()) {
                try {
                    getSfConfig().getPartnerConnection().logout();
                } catch (Exception e) {
                    LOG.warn("Error while closing PartnerConnection", e);
                }
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

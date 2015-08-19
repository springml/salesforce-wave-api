package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.api.WaveAPI;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Default Implementation for {@link WaveAPI}
 */
@JsonIgnoreProperties
public class WaveAPIImpl implements WaveAPI {
    private static final Logger LOG = Logger.getLogger(HTTPHelper.class);

    private ObjectMapper objectMapper;
    private HTTPHelper httpHelper;
    private SFConfig sfConfig;

    public WaveAPIImpl(SFConfig sfConfig) throws Exception {
        setHttpHelper(new HTTPHelper());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        setObjectMapper(objectMapper);

        this.sfConfig = sfConfig;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public HTTPHelper getHttpHelper() {
        return httpHelper;
    }

    public void setHttpHelper(HTTPHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public SFConfig getSfConfig() {
        return sfConfig;
    }

    public void setSfConfig(SFConfig sfConfig) {
        this.sfConfig = sfConfig;
    }

    public QueryResult query(String saql) throws Exception {
        PartnerConnection connection = getSfConfig().createPartnerConnection();
        try {
            Map<String, String> saqlMap = new HashMap<String, String>(4);
            saqlMap.put(STR_QUERY, saql);
            String request = getObjectMapper().writeValueAsString(saqlMap);

            String queryURL = getRequestURL(connection, SERVICE_PATH_QUERY);
            String response = getHttpHelper().post(queryURL, getSessionId(connection), request);
            LOG.debug("Query Response from server " + response);
            return getObjectMapper().readValue(response.getBytes(), QueryResult.class);
        } finally {
            if (connection != null) {
                try {
                    connection.logout();
                } catch (Exception e) {
                    LOG.warn("Error while closing PartnerConnection", e);
                }
            }
        }
    }

    private String getSessionId(PartnerConnection connection) {
        return connection.getConfig().getSessionId();
    }

    private String getRequestURL(PartnerConnection connection, String path) throws URISyntaxException {
        URI seURI = new URI(connection.getConfig().getServiceEndpoint());

        return new URI(seURI.getScheme(),seURI.getUserInfo(), seURI.getHost(), seURI.getPort(),
                path, null, null).toString();
    }
}

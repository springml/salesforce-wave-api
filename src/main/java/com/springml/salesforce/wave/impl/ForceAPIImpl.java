package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;

import org.apache.log4j.Logger;

import com.sforce.soap.partner.PartnerConnection;
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
        // Connection created to get the sessionId
        PartnerConnection connection = getSfConfig().createPartnerConnection();
        try {
            StringBuilder queryParam = new StringBuilder();
            queryParam.append(QUERY_PARAM);
            queryParam.append(soql);
            URI queryURI = getSfConfig().getRequestURI(connection, SERVICE_PATH_QUERY, queryParam.toString());
            String response = getHttpHelper().get(queryURI, getSfConfig().getSessionId(connection));

            LOG.debug("Query Response from server " + response);
            return getObjectMapper().readValue(response.getBytes(), SOQLResult.class);
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

}

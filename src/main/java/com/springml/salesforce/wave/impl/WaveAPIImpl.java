package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.api.WaveAPI;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Default Implementation for {@link WaveAPI}
 */
@JsonIgnoreProperties
public class WaveAPIImpl extends AbstractAPIImpl implements WaveAPI {
    private static final Logger LOG = Logger.getLogger(WaveAPIImpl.class);

    public WaveAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public QueryResult query(String saql) throws Exception {
        SFConfig sfConfig = getSfConfig();
        PartnerConnection connection = sfConfig.getPartnerConnection();
        try {
            Map<String, String> saqlMap = new HashMap<String, String>(4);
            saqlMap.put(STR_QUERY, saql);
            String request = getObjectMapper().writeValueAsString(saqlMap);

            String waveQueryPath = getWaveQueryPath(sfConfig);
            URI queryURI = sfConfig.getRequestURI(connection, waveQueryPath);
            String response = getHttpHelper().post(queryURI, getSfConfig().getSessionId(connection), request);
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

    private String getWaveQueryPath(SFConfig sfConfig) {
        StringBuilder waveQueryPath = new StringBuilder();
        waveQueryPath.append(SERVICE_PATH);
        waveQueryPath.append("v");
        waveQueryPath.append(sfConfig.getApiVersion());
        waveQueryPath.append(PATH_WAVE_QUERY);

        return waveQueryPath.toString();
    }

}

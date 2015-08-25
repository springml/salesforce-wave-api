package com.springml.salesforce.wave.api;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.SFConfig;

public abstract class BaseAPITest {
    protected static final String SERVICE_ENDPOINT = "http://gs0.salesforce.com";
    protected static final String SESSION_ID = "dummySessionId";

    protected SFConfig sfConfig = null;
    protected HTTPHelper httpHelper = null;
    protected ObjectMapper objectMapper = null;
    protected PartnerConnectionExt conn = null;

    public void setup() throws Exception {
        conn = PartnerConnectionExt.getInstance();

        sfConfig = mock(SFConfig.class);
        when(sfConfig.createPartnerConnection()).thenReturn(conn);

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    }

    public static class PartnerConnectionExt extends PartnerConnection {
        private ConnectorConfig config;

        public static PartnerConnectionExt getInstance() throws ConnectionException {
            ConnectorConfig config = new ConnectorConfig();
            config.setUsername("dummy_sf_user");
            config.setPassword("dummy_sf_password");
            config.setManualLogin(true);
            // Salesforce SOAP API checks for /services/Soap/c/
            config.setServiceEndpoint("http://dummysgendpoint/services/Soap/u/");

            return new PartnerConnectionExt(config);
        }

        public PartnerConnectionExt(ConnectorConfig config)
                throws ConnectionException {
            super(config);
            this.config = config;
        }

        @Override
        public ConnectorConfig getConfig() {
            config.setSessionId(SESSION_ID);
            config.setServiceEndpoint(SERVICE_ENDPOINT);
            return config;
        }
    }
}

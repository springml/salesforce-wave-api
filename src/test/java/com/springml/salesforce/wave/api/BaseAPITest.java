/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
 * Contributors  :
 * 	  Samual Alexander, springml  
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
package com.springml.salesforce.wave.api;

import static org.mockito.Mockito.*;

import org.apache.log4j.BasicConfigurator;

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
    protected static final String API_VERSION = "36.0";

    protected SFConfig sfConfig = null;
    protected HTTPHelper httpHelper = null;
    protected ObjectMapper objectMapper = null;
    protected PartnerConnectionExt conn = null;

    public void setup() throws Exception {
    	BasicConfigurator.configure();
        conn = PartnerConnectionExt.getInstance();

        sfConfig = mock(SFConfig.class);
        when(sfConfig.getPartnerConnection()).thenReturn(conn);
        when(sfConfig.getSessionId()).thenReturn(SESSION_ID);
        when(sfConfig.getApiVersion()).thenReturn(API_VERSION);

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

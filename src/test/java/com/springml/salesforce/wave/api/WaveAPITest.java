package com.springml.salesforce.wave.api;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.springml.salesforce.wave.impl.WaveAPIImpl;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.model.Results;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.SFConfig;
import com.springml.salesforce.wave.util.WaveAPIConstants;

public class WaveAPITest {
    private static final String SERVICE_ENDPOINT = "http://gs0.salesforce.com";
    private static final String SESSION_ID = "dummySessionId";
    private static final String SAQL = "q = load \"0FbB000000007qmKAA/0FcB00000000LgTKAU\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;";
    private static final String RESPONSE_JSON = "{\"action\":\"query\",\"responseId\":\"3zj8MTVk6xJPifVeRhqeek\",\"results\":{\"records\":[{\"count\":11,\"device_type\":\"Android 4.2\",\"event\":\"Click\"},{\"count\":9,\"device_type\":\"Android 4.3.1\",\"event\":\"Click\"},{\"count\":49,\"device_type\":\"iPod\",\"event\":\"Impression\"}]},\"query\":\"q = load \\\"0FbB000000007qmKAA/0FcB00000000LgTKAU\\\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;\",\"responseTime\":85}";

    private SFConfig sfConfig = null;
    private HTTPHelper httpHelper = null;
    private ObjectMapper objectMapper = null;

    @Before
    public void setup() throws Exception {
        sfConfig = mock(SFConfig.class);
        when(sfConfig.createPartnerConnection()).thenReturn(PartnerConnectionExt.getInstance());

        Map<String, String> saqlMap = new HashMap<String, String>(4);
        saqlMap.put(STR_QUERY, SAQL);
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String request = objectMapper.writeValueAsString(saqlMap);
        httpHelper = mock(HTTPHelper.class);
        when(httpHelper.post("http://gs0.salesforce.com" + WaveAPIConstants.SERVICE_PATH_QUERY, SESSION_ID, request))
                .thenReturn(RESPONSE_JSON);
    }

    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryWithoutMock() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("real_sf_username",
                "real_sf_password", "https://login.salesforce.com");

        QueryResult result = waveAPI.query(SAQL);
        System.out.println(result.getResults().getRecords().get(0).get("count"));
        System.out.println("result :  " + result);
        assertEquals(12, result.getResults().getRecords().size());
        assertEquals("11", result.getResults().getRecords().get(0).get("count"));
    }

    @Test
    public void testQuery() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com");
        ((WaveAPIImpl) waveAPI).setHttpHelper(httpHelper);
        ((WaveAPIImpl) waveAPI).setSfConfig(sfConfig);
        ((WaveAPIImpl) waveAPI).setObjectMapper(objectMapper);

        QueryResult result = waveAPI.query(SAQL);
        assertNotNull(result);
        Results results = result.getResults();
        assertNotNull(results);
        List<Map<String,String>> records = results.getRecords();
        assertNotNull(records);
        assertTrue(!records.isEmpty());
        assertEquals(3, records.size());
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
package com.springml.salesforce.wave.api;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.springml.salesforce.wave.impl.WaveAPIImpl;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.model.Results;
import com.springml.salesforce.wave.util.HTTPHelper;

public class WaveAPITest extends BaseAPITest {
    private static final String SAQL = "q = load \"0FbB000000007qmKAA/0FcB00000000LgTKAU\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;";
    private static final String RESPONSE_JSON = "{\"action\":\"query\",\"responseId\":\"3zj8MTVk6xJPifVeRhqeek\",\"results\":{\"records\":[{\"count\":11,\"device_type\":\"Android 4.2\",\"event\":\"Click\"},{\"count\":9,\"device_type\":\"Android 4.3.1\",\"event\":\"Click\"},{\"count\":49,\"device_type\":\"iPod\",\"event\":\"Impression\"}]},\"query\":\"q = load \\\"0FbB000000007qmKAA/0FcB00000000LgTKAU\\\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;\",\"responseTime\":85}";

    @Before
    public void setup() throws Exception {
        super.setup();
        httpHelper = mock(HTTPHelper.class);
        when(httpHelper.post(any(URI.class), any(String.class), any(String.class)))
                .thenReturn(RESPONSE_JSON);
    }

    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryWithoutMock() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("samspark@palmtreeinfotech.com",
                "Fire2015!uRu7NN7L99uIiRZr9VCngTCg", "https://login.salesforce.com");

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

}
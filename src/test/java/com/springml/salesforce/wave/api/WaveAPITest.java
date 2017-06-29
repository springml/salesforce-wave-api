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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.impl.WaveAPIImpl;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.model.Results;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.WaveAPIConstants;

public class WaveAPITest extends BaseAPITest {
    private static final String SAQL = "q = load \"0FbB000000007qmKAA/0FcB00000000LgTKAU\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;";
    private static final String SAQL_QUERY_MORE = "q = load \"0Fb15000000TOLuCAO/0Fc15000000TvvCCAS\"; q = group q by ('Name', 'NPI'); q = foreach q generate 'Name' as 'Name', 'NPI' as 'My NPI', count() as 'count';";
    private static final String RESPONSE_JSON = "{\"action\":\"query\",\"responseId\":\"3zj8MTVk6xJPifVeRhqeek\",\"results\":{\"records\":[{\"count\":11,\"device_type\":\"Android 4.2\",\"event\":\"Click\"},{\"count\":9,\"device_type\":\"Android 4.3.1\",\"event\":\"Click\"},{\"count\":49,\"device_type\":\"iPod\",\"event\":\"Impression\"}]},\"query\":\"q = load \\\"0FbB000000007qmKAA/0FcB00000000LgTKAU\\\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;\",\"responseTime\":85}";
    private static final String PAGINATED_QUERY_RESPONSE_JSON = "{\"action\":\"query\",\"responseId\":\"41F6l5blC1DT4gVUOh_zRk\",\"results\":{\"records\":[{\"My NPI\":\"0\",\"Name\":\"Pat 0\",\"count\":1},{\"My NPI\":\"1\",\"Name\":\"Pat 1\",\"count\":1}]},\"query\":\"q = load \\\"0Fb15000000TOLuCAO/0Fc15000000TvvCCAS\\\"; q = group q by ('Name', 'NPI'); q = foreach q generate 'Name' as 'Name', 'NPI' as 'My NPI', count() as 'count'; q = limit q 2;\",\"responseTime\":896}";
    private static final String QUERY_MORE_RESPONSE_JSON = "{\"action\":\"query\",\"responseId\":\"41FF6eV0Xgq1FrH5ThIld-\",\"results\":{\"records\":[]},\"query\":\"q = load \\\"0Fb15000000TOLuCAO/0Fc15000000TvvCCAS\\\"; q = group q by ('Name', 'NPI'); q = foreach q generate 'Name', 'NPI', count() as 'count';q = offset q 10000000; q = limit q 100; \",\"responseTime\":19612}";

    @Before
    public void setup() throws Exception {
        super.setup();
        httpHelper = mock(HTTPHelper.class);
        when(httpHelper.post((URI) ArgumentMatchers.isNull(), (String) ArgumentMatchers.isNull(), any(String.class)))
                .thenReturn(RESPONSE_JSON);
    }

    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryWithoutMock() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("xxx@xxx.com",
                "***", "https://login.salesforce.com");

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


    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryMoreWithoutMock() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("xxx@xxx.com",
                "***", "https://login.salesforce.com");

        QueryResult result = waveAPI.queryWithPagination(SAQL_QUERY_MORE, "q", 10);
        System.out.println("result :  " + result);
        assertEquals(10, result.getResults().getRecords().size());
        assertTrue(!result.isDone());

        QueryResult queryMoreResult = waveAPI.queryMore(result);
        System.out.println("queryMoreResult :  " + queryMoreResult);
        assertEquals(10, queryMoreResult.getResults().getRecords().size());
        assertTrue(!queryMoreResult.isDone());
    }

    @Test
    public void testQueryMore() throws Exception {
        when(httpHelper.post((URI) ArgumentMatchers.isNull(), (String) ArgumentMatchers.isNull(), any(String.class))).thenReturn(PAGINATED_QUERY_RESPONSE_JSON);
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com");
        ((WaveAPIImpl) waveAPI).setHttpHelper(httpHelper);
        ((WaveAPIImpl) waveAPI).setSfConfig(sfConfig);
        ((WaveAPIImpl) waveAPI).setObjectMapper(objectMapper);

        QueryResult result = waveAPI.queryWithPagination(SAQL_QUERY_MORE, "q", 2);
        assertNotNull(result);
        Results results = result.getResults();
        assertNotNull(results);
        List<Map<String,String>> records = results.getRecords();
        assertNotNull(records);
        assertTrue(!records.isEmpty());
        assertEquals(2, records.size());        
        assertTrue(!result.isDone());

        when(httpHelper.post((URI) ArgumentMatchers.isNull(), (String) ArgumentMatchers.isNull(), any(String.class))).thenReturn(QUERY_MORE_RESPONSE_JSON);
        QueryResult queryMoreResult = waveAPI.queryMore(result);
        assertTrue(queryMoreResult.isDone());  
    }

    @Test
    public void testGetDatasetId() throws Exception {
        String datasetName = "Account";
        String datasetEndpoint = SERVICE_ENDPOINT + "/services/data/v" + sfConfig.getApiVersion() +
                WaveAPIConstants.PATH_WAVE_DATASETS + "?q=" + datasetName;

        InputStream responseIS = this.getClass().getClassLoader().getResourceAsStream("dataset_response.json");
        String response = IOUtils.toString(responseIS, "UTF-8");

        when(sfConfig.getRequestURI(any(PartnerConnection.class), any(String.class))).thenCallRealMethod();
        when(sfConfig.getRequestURI(any(PartnerConnection.class), any(String.class), any(String.class))).thenCallRealMethod();
        when(httpHelper.get(new URI(datasetEndpoint), SESSION_ID)).thenReturn(response);

        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com");
        ((WaveAPIImpl) waveAPI).setHttpHelper(httpHelper);
        ((WaveAPIImpl) waveAPI).setSfConfig(sfConfig);
        ((WaveAPIImpl) waveAPI).setObjectMapper(objectMapper);

        String datasetId = waveAPI.getDatasetId(datasetName);
        assertNotNull(datasetId);
        String expectedDatasetId = "0FbB00000000KybKAE/0FcB0000000DGKeKAO";
        assertEquals("DatasetId does not match", expectedDatasetId, datasetId);
    }

}

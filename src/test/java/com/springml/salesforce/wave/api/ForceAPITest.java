package com.springml.salesforce.wave.api;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.springml.salesforce.wave.impl.ForceAPIImpl;
import com.springml.salesforce.wave.model.SOQLResult;
import com.springml.salesforce.wave.util.HTTPHelper;

public class ForceAPITest extends BaseAPITest {
    private static final String SOQL = "SELECT AccountId, Id, ProposalID__c FROM Opportunity where ProposalID__c != null";
    private static final String QUERY_MORE_SOQL = "SELECT Id, OwnerId, Name, Player_Id__c, Phone__c FROM player__c";
    private static final String RESPONSE_JSON = "{\"totalSize\":4,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"Opportunity\",\"url\":\"/services/data/v34.0/sobjects/Opportunity/006B0000002ndnuIAA\"},\"AccountId\":\"001B0000003oYAfIAM\",\"Id\":\"006B0000002ndnuIAA\",\"ProposalID__c\":\"103\"},{\"attributes\":{\"type\":\"Opportunity\",\"url\":\"/services/data/v34.0/sobjects/Opportunity/006B0000002ndnpIAA\"},\"AccountId\":\"001B0000003oYAfIAM\",\"Id\":\"006B0000002ndnpIAA\",\"ProposalID__c\":\"102\"}]}";

    @Before
    public void setup() throws Exception {
        super.setup();
        StringBuilder queryParam = new StringBuilder();
        queryParam.append(QUERY_PARAM);
        queryParam.append(SOQL);
        when(sfConfig.getRequestURI(conn, SERVICE_PATH_QUERY, queryParam.toString())).thenCallRealMethod();

        httpHelper = mock(HTTPHelper.class);
        when(httpHelper.get(any(URI.class), any(String.class))).thenReturn(RESPONSE_JSON);
    }

    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryWithoutMock() throws Exception {
        ForceAPI forceAPI = APIFactory.getInstance().forceAPI("xxx@xxx.com",
                "***", "https://login.salesforce.com");

        SOQLResult result = forceAPI.query(SOQL);
        System.out.println("result :  " + result);
        System.out.println("records :  " + result.getRecords());
        System.out.println("records size :  " + result.getRecords().size());
        System.out.println(result.getRecords().get(0).get("ProposalID__c"));
        assertEquals(4, result.getRecords().size());
        assertEquals("103", result.getRecords().get(0).get("ProposalID__c"));
        assertTrue(result.isDone());
    }

    @Test
    public void testQuery() throws Exception {
        ForceAPI forceAPI = APIFactory.getInstance().forceAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com");
        ((ForceAPIImpl) forceAPI).setHttpHelper(httpHelper);
        ((ForceAPIImpl) forceAPI).setSfConfig(sfConfig);
        ((ForceAPIImpl) forceAPI).setObjectMapper(objectMapper);

        SOQLResult result = forceAPI.query(SOQL);
        assertNotNull(result);
        List<Map<String,Object>> records = result.getRecords();
        assertNotNull(records);
        assertTrue(!records.isEmpty());
        assertEquals(2, records.size());
        Map<String, Object> values = records.get(1);
        assertEquals("006B0000002ndnpIAA", values.get("Id"));
        assertTrue(result.isDone());
    }

    @Test
    @Ignore("This can be only executed with actual salesforce username and password")
    public void testQueryMoreWithoutMock() throws Exception {
        ForceAPI forceAPI = APIFactory.getInstance().forceAPI("xxx@xxx.com",
                "***", "https://login.salesforce.com");

	System.out.println("Executing QueryMoreWithoutLock.......");
        SOQLResult result = forceAPI.query(QUERY_MORE_SOQL);
        System.out.println("records size :  " + result.getRecords().size());
        assertEquals(2000, result.getRecords().size());
        assertEquals(false, result.isDone());

        SOQLResult newResults = forceAPI.queryMore(result);
        assertEquals(2000, newResults.getRecords().size());
        assertEquals(false, newResults.isDone());
    }

    @Test
    public void testQueryMore() throws Exception {
        ForceAPI forceAPI = APIFactory.getInstance().forceAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com");
        ((ForceAPIImpl) forceAPI).setHttpHelper(httpHelper);
        ((ForceAPIImpl) forceAPI).setSfConfig(sfConfig);
        ((ForceAPIImpl) forceAPI).setObjectMapper(objectMapper);

        SOQLResult result = forceAPI.query(SOQL);
        assertNotNull(result);
        List<Map<String,Object>> records = result.getRecords();
        assertNotNull(records);
        assertTrue(!records.isEmpty());
        assertEquals(2, records.size());
        Map<String, Object> values = records.get(1);
        assertEquals("006B0000002ndnpIAA", values.get("Id"));
        assertEquals(true, result.isDone());

        try {
            forceAPI.queryMore(result);
            fail("Exception is not thrown when queryMore executed for a done result");
        } catch (Exception e) {
            // Expected
        }
    }
}
package com.springml.salesforce.wave.api;

import static org.junit.Assert.*;
import org.junit.Test;

public class APIFactoryTest {

    @Test
    public void waveAPITest() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("username", "password", "http://login.salesforce.com");
        assertNotNull(waveAPI);
    }

    @Test
    public void forceAPITest() throws Exception {
        ForceAPI forceAPI = APIFactory.getInstance().forceAPI("username", "password", "http://login.salesforce.com");
        assertNotNull(forceAPI);
    }

    @Test
    public void bulkAPITest() throws Exception {
        BulkAPI bulkAPI = APIFactory.getInstance().bulkAPI("username", "password", "http://login.salesforce.com", "36.0");
        assertNotNull(bulkAPI);
    }
}

package com.springml.salesforce.wave.api;

import static org.junit.Assert.*;
import org.junit.Test;

public class APIFactoryTest {

    @Test
    public void waveAPITest() throws Exception {
        WaveAPI waveAPI = APIFactory.getInstance().waveAPI("username", "password", "http://login.salesforce.com");
        assertNotNull(waveAPI);
    }
}

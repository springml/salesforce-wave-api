package com.springml.salesforce.wave.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Simple test for {@link HTTPHelper}
 *
 */
public class HTTPHelperTest {

    @Test
    public void testPost() throws Exception {
        HTTPHelper httpHelper = new HTTPHelper();
        String request = "{\"query\":\"q = load \\\"0FbB000000007qmKAA/0FcB00000000LgTKAU\\\"; q = group q by ('event', 'device_type'); q = foreach q generate 'event' as 'event',  'device_type' as 'device_type', count() as 'count'; q = limit q 2000;\"}";
        String response = httpHelper.post("http://jsonplaceholder.typicode.com/posts", "sessionId", request);

        assertTrue(response.contains("device_type"));
    }
}

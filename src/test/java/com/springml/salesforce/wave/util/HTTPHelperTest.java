package com.springml.salesforce.wave.util;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;
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
        URI requestURI = new URI("http://jsonplaceholder.typicode.com/posts");
        String response = httpHelper.post(requestURI, "sessionId", request);

        assertTrue(response.contains("device_type"));
    }

    @Test
    public void testGet() throws Exception {
        HTTPHelper httpHelper = new HTTPHelper();
        URI requestURI = new URI("http://jsonplaceholder.typicode.com/posts");
        String response = httpHelper.get(requestURI, "sessionId");

        assertTrue(response.contains("userId"));
    }
}

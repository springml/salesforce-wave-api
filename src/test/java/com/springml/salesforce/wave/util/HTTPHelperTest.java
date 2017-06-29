/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
 * Contributors  :
 * 	  Samual Alexander, springml  
 *    Kagan Turgut,  oolong
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

package com.springml.salesforce.wave.util;

import static org.junit.Assert.*;

import java.net.URI;

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

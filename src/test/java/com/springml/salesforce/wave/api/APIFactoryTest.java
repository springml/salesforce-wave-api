/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
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

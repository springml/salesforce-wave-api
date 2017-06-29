/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml, oolong
 * Contributors  :
 * 	  Samual Alexander, springml  
 *    Kagan Turgut
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

import com.springml.salesforce.wave.impl.BulkAPIImpl;
import com.springml.salesforce.wave.impl.ChatterAPIImpl;
import com.springml.salesforce.wave.impl.ForceAPIImpl;
import com.springml.salesforce.wave.impl.WaveAPIImpl;
import com.springml.salesforce.wave.util.SFConfig;
import com.springml.salesforce.wave.util.WaveAPIConstants;

/**
 * Factory class to get WaveAPI
 */
public class APIFactory {
    private static APIFactory instance = null;

    private APIFactory() {}

    public static APIFactory getInstance() {
        if (instance == null) {
            instance = new APIFactory();
        }

        return instance;
    }

    public WaveAPI waveAPI(String username, String password, String loginURL) throws Exception {
        return this.waveAPI(username, password, loginURL, WaveAPIConstants.API_VERSION);
    }

    public WaveAPI waveAPI(String username, String password, String loginURL, String apiVersion) throws Exception {
        return new WaveAPIImpl(new SFConfig(username, password, loginURL, apiVersion));
    }

    public ForceAPI forceAPI(String username, String password, String loginURL) throws Exception {
        return this.forceAPI(username, password, loginURL, WaveAPIConstants.API_VERSION);
    }

    public ForceAPI forceAPI(String username, String password, String loginURL, String apiVersion) throws Exception {
        return new ForceAPIImpl(new SFConfig(username, password, loginURL, apiVersion));
    }

    public ForceAPI forceAPI(String username, String password, String loginURL,
            String apiVersion, Integer batchSize) throws Exception {
        return new ForceAPIImpl(new SFConfig(username, password, loginURL, apiVersion, batchSize));
    }

    public ForceAPI forceAPI(String username, String password, String loginURL,
            String apiVersion, Integer batchSize, Integer maxRetry) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, loginURL, apiVersion, batchSize);
        sfConfig.setMaxRetry(maxRetry);
        return new ForceAPIImpl(sfConfig);
    }

    public BulkAPI bulkAPI(String username, String password, String loginURL, String apiVersion) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, loginURL, apiVersion);
        return new BulkAPIImpl(sfConfig);
    }

    public ChatterAPI chatterAPI(String username, String password, String loginURL, String apiVersion) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, loginURL, apiVersion);
        return new ChatterAPIImpl(sfConfig);
    }
}

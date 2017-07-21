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

    public ForceAPI forceAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL) throws Exception {
        return this.forceAPI(username, password, useBearerToken, bearerToken, loginURL, WaveAPIConstants.API_VERSION);
    }

    public ForceAPI forceAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL, String apiVersion) throws Exception {
        return new ForceAPIImpl(new SFConfig(username, password, useBearerToken, bearerToken, loginURL, apiVersion, null));
    }

    public ForceAPI forceAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL,
            String apiVersion, Integer batchSize) throws Exception {
        return new ForceAPIImpl(new SFConfig(username, password, useBearerToken, bearerToken, loginURL, apiVersion, batchSize));
    }

    public ForceAPI forceAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL,
            String apiVersion, Integer batchSize, Integer maxRetry) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, useBearerToken, bearerToken, loginURL, apiVersion, batchSize);
        sfConfig.setMaxRetry(maxRetry);
        return new ForceAPIImpl(sfConfig);
    }
    
    public BulkAPI bulkAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL, String apiVersion) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, useBearerToken, bearerToken, loginURL, apiVersion, null);
        return new BulkAPIImpl(sfConfig);
    }

    public BulkAPI bulkAPI(String username, String password, boolean useBearerToken, String bearerToken, String loginURL, String apiVersion, String concurrencyMode) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, useBearerToken, bearerToken, loginURL, apiVersion, null);
        sfConfig.setConcurrencyMode(concurrencyMode);
        return new BulkAPIImpl(sfConfig);
    }

    public ChatterAPI chatterAPI(String username, String password, String loginURL, String apiVersion) throws Exception {
        SFConfig sfConfig = new SFConfig(username, password, loginURL, apiVersion);
        return new ChatterAPIImpl(sfConfig);
    }
}

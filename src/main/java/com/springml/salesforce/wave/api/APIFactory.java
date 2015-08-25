package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.impl.ForceAPIImpl;
import com.springml.salesforce.wave.impl.WaveAPIImpl;
import com.springml.salesforce.wave.util.SFConfig;

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
        return new WaveAPIImpl(new SFConfig(username, password, loginURL));
    }

    public ForceAPI forceAPI(String username, String password, String loginURL) throws Exception {
        return new ForceAPIImpl(new SFConfig(username, password, loginURL));
    }
}

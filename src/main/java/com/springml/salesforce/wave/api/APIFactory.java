package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.impl.WaveAPIImpl;

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
		return new WaveAPIImpl(username, password, loginURL);
	}
}

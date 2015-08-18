package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.QueryResult;

/**
 * Java interface for WAVE REST calls
 */
public interface WaveAPI {
	/**
	 * Execute the given SAQL by using "/wave/query" API
	 * @param saql - SAQL to be executed. This will be converted into JSON
	 * @return {@link QueryResult}
	 * @throws Exception 
	 */
	public QueryResult query(String saql) throws Exception;
}

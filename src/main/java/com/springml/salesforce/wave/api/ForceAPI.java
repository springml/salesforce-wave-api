package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.model.SOQLResult;

/**
 * JAVA client for Salesforce REST API calls
 */
public interface ForceAPI {
    /**
     * Execute the given SOQL by using "/query" API
     * @param soql - SOQL to be executed.
     * @return {@link QueryResult}
     * @throws Exception
     */
    public SOQLResult query(String soql) throws Exception;

    /**
     * Query further records using nextRecordsURL
     * @param oldResult 
     * @return
     * @throws Exception
     */
    public SOQLResult queryMore(SOQLResult oldResult) throws Exception;
}

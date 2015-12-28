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

    /**
     * Executes Paginated query and return the result
     * To get further results call WaveAPI.queryMore(QueryResult queryResult)
     * @param saql
     * @param resultVar
     * @param pageSize
     * @return
     * @throws Exception
     */
    public QueryResult queryWithPagination(String saql, String resultVar, int pageSize) throws Exception;

    /**
     * Execute query and return the next set of results
     * @param queryResult
     * @return
     * @throws Exception
     */
    public QueryResult queryMore(QueryResult queryResult) throws Exception;

}

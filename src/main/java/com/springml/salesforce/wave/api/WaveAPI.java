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

    /**
     * Returns DatasetId appended with VersionId separated by /
     * @param datasetName Name of the dataset for which datasetId and versionId to be fetched
     * @return datasetId appended with versionId separated by /
     * @throws Exception
     */
    public String getDatasetId(String datasetName) throws Exception;
}

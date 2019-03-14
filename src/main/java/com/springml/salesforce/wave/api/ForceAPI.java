package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.AddTaskRequest;
import com.springml.salesforce.wave.model.AddTaskResponse;
import com.springml.salesforce.wave.model.DescribeSObjectResult;
import com.springml.salesforce.wave.model.ForceResponse;
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
     * Execute the given SOQL by using either the "/query" API
     * or the "/queryAll" API
     * @param soql - SOQL to be executed.
     * @param all - Toggle for /query or /queryAll
     * @return {@link QueryResult}
     * @throws Exception
     */
    public SOQLResult query(String soql, boolean all) throws Exception;

    /**
     * Query further records using nextRecordsURL
     * @param oldResult
     * @return
     * @throws Exception
     */
    public SOQLResult queryMore(SOQLResult oldResult) throws Exception;

    /**
     * Creates task with given details in salesforce
     * @return
     */
    public AddTaskResponse addTask(AddTaskRequest addTask) throws Exception;

    /**
     * Insert a salesforce object
     * @param object - Name of the salesforce object
     * @param content - Json content of the object to be saved
     */
    public ForceResponse insertObject(String object, String content) throws Exception;

    /**
     * Insert a salesforce object
     * @param object - Name of the salesforce object
     * @param content - Json content of the object to be saved
     */
//    public InsertObjectsResponse insertObjects(String object, List<String> objects) throws Exception;

    public String getSFEndpoint() throws Exception;

    /**
     * Returns list of the salesforce object column names
     * @param object Name of the salesforce object
     * @return
     * @throws Exception
     */
    public DescribeSObjectResult describeSalesforceObject(String object) throws Exception;
}

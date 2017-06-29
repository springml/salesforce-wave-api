/*
 * Copyright 2015 - 2017, salesforce-wave-api, oolong, springml
 * Contributors  :
 *    Kagan Turgut, oolong
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

import java.io.InputStream;
import java.util.List;

import com.springml.salesforce.wave.model.BatchInfo;
import com.springml.salesforce.wave.model.BatchInfoList;
import com.springml.salesforce.wave.model.BatchResult;
import com.springml.salesforce.wave.model.JobInfo;
import com.springml.salesforce.wave.model.SOQLResult;

/**
 * Java client for Salesforce Bulk API
 * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_intro.htm
 */
public interface BulkAPI {
    /**
     * Create a new Bulk Job
     * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm
     * @param The Salesforce object to be updated
     * @return @JobInfo
     * @throws Exception
     * @deprecated Use createUpdateJob or createQueryJob instead
     */
    public JobInfo createJob(String object) throws Exception;
    
    /**
     * Create Update Job
     * @param object
     * @return
     * @throws Exception
     */
    public JobInfo createUpdateJob(String object) throws Exception;
    
    /**
     * 
     * @param object
     * @return
     * @throws Exception
     */
    public JobInfo createQueryJob(String object) throws Exception;
    
    /**
     * Create bulk query job with PK chunking
     * @param object
     * @param pkChunking
     * @return
     * @throws Exception
     */
    public JobInfo createQueryJob(String object, Boolean pkChunking) throws Exception;
    
    
    /**
     * Create bulk query job with PK chunking. if QueryAll is set, it will bring deleted Salesforce records as well.
     * @param object     : name of the object in salesforce
     * @param pkChunking : whether the result will be returned in batches
     * @param queryAll   : whether to bring deleted records from salesforce. subject to Salesforce garbage bin limitations
     * @return
     * @throws Exception
     */
    public JobInfo createQueryJob(String object, Boolean pkChunking, Boolean queryAll) throws Exception;
    
    /**
     * Create bulk query job with PK chunking. if QueryAll is set, it will bring deleted Salesforce records as well.
     * Use this method if you want to specify additional pkChunking parameters such as chunkSize.
     * @param object
     * @param pkChunkingKey
     * @param pkChunkingValue : you can optionally pass chunksize, but better to go with the default.
     * @param queryAll : if true, it will bring deleted records as well.
     * @return
     * @throws Exception
     */
    public JobInfo createQueryJob(String object, String pkChunkingKey, String pkChunkingValue, Boolean queryAll) throws Exception;


    /**
     * Create a new Bulk Job
     * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm
     * @param The Salesforce object to be updated
     * @return @JobInfo
     * @throws Exception
     */
    public JobInfo createJob(String object, String operation, String contentType) throws Exception;

    /**
     * Create a new Bulk Job
     * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm
     * @param jobInfo with details of the Job to be created
     * @return {@link JobInfo}
     * @throws Exception
     */
    public JobInfo createJob(JobInfo jobInfo) throws Exception;
    

    /**
     * Add a batch to an existing Job
     * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_add_batch.htm
     * @param jobId Salesforce Job Id
     * @param csvContent CSV Content which will be used to update salesforce objects
     * @return {@link BatchInfo}
     * @throws Exception
     */
    public BatchInfo addBatch(String jobId, String csvContent) throws Exception;
    
    /**
     * Close the job
     * @param jobId
     * @param checkBatchesFirst
     * @return
     * @throws Exception
     */
	public JobInfo closeJob(String jobId, Boolean checkBatchesFirst) throws Exception;

    /**
     * Close the specified Salesforce Bulk Job
     * @param jobId Job to be closed
     * @return
     * @throws Exception
     */
    public JobInfo closeJob(String jobId) throws Exception;

    /**
     * Queries Salesforce to get the BatchInfo of the specified Job
     * Check whether all the Jobs are completed
     * @param jobId Job to be checked for its status
     * @return true - if all batches are completed else false
     * @throws Exception
     */
    public boolean isCompleted(String jobId) throws Exception;

    /**
     * Queries Salesforce to get the BatchInfo of the specified Job
     * Check whether all the Jobs are completed
     * @param jobId Job to be checked for its status
     * @return true - if all batches are completed else false
     * @throws Exception
     */
    public boolean isSuccess(String jobId) throws Exception;

    /**
     * List of the Batches for the specified Job
     * @param jobId
     * @return
     * @throws Exception
     */
    public BatchInfoList getBatchInfoList(String jobId) throws Exception;

    /**
     * Provides details of the specified Batch
     * @param jobId
     * @param batchId
     * @return
     * @throws Exception
     */
    public BatchInfo getBatchInfo(String jobId, String batchId) throws Exception;
    
    /**
     * Get list of active jobs
     * @return
     */
    public List<String> getActiveJobIds();

    /**
     * Get list of recently completed jobs
     * @return
     */
    public List<String> getCompletedJobIds();
    
    /**
     * Return batchResults for this batch. This is in CSV format, and by default includes the header rows
     * @param batch
     * @return
     * @throws Exception
     */
    public BatchResult queryBatch(BatchInfo batch) throws Exception;
    
    /**
     * Return batchresults as a stream. CSV format, includes the header rows by default.
     * @param batch
     * @return
     * @throws Exception
     */
    public InputStream queryBatchStream(BatchInfo batch) throws Exception;


}

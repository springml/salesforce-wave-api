package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.BatchInfo;
import com.springml.salesforce.wave.model.BatchInfoList;
import com.springml.salesforce.wave.model.JobInfo;
import org.apache.http.Header;
import java.util.List;

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
     */
    public JobInfo createJob(String object) throws Exception;

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
     * Create a new Bulk Job
     * https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/asynch_api_quickstart_create_job.htm
     * @param jobInfo with details of the Job to be created
     * @param customHeaders Custom headers for job. These headers will be appended to default headers.
     * @return {@link JobInfo}
     * @throws Exception
     */
    public JobInfo createJob(JobInfo jobInfo, List<Header> customHeaders) throws Exception;

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
     * Retrieves a list of batch result IDs for a particular batch job.
     * @param jobId The identifier for the job
     * @param batchId The batch identifier for the job
     * @return
     * @throws Exception
     */
    public List<String> getBatchResultIds(String jobId, String batchId) throws Exception;

    /**
     * Gets bulk query results
     * @param jobId The identifier for the job
     * @param batchId The batch identifier for the job
     * @param resultId the result ID in the response to the batch result list request
     * @return
     * @throws Exception
     */
    public String getBatchResult(String jobId, String batchId, String resultId) throws Exception;
}

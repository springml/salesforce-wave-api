package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.CONTENT_TYPE_APPLICATION_JSON;
import static com.springml.salesforce.wave.util.WaveAPIConstants.CONTENT_TYPE_APPLICATION_XML;
import static com.springml.salesforce.wave.util.WaveAPIConstants.CONTENT_TYPE_TEXT_CSV;
import static com.springml.salesforce.wave.util.WaveAPIConstants.PATH_BATCH;
import static com.springml.salesforce.wave.util.WaveAPIConstants.PATH_JOB;
import static com.springml.salesforce.wave.util.WaveAPIConstants.PATH_RESULT;
import static com.springml.salesforce.wave.util.WaveAPIConstants.SERVICE_ASYNC_PATH;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_CLOSED;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_COMPLETED;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_CSV;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_FAILED;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_JSON;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_PARALLEL;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_QUERY;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_QUERY_ALL;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_UPDATE;
import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_XML;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.api.BulkAPI;
import com.springml.salesforce.wave.model.BatchInfo;
import com.springml.salesforce.wave.model.BatchInfoList;
import com.springml.salesforce.wave.model.BatchResult;
import com.springml.salesforce.wave.model.JobInfo;
import com.springml.salesforce.wave.util.LRUCache;
import com.springml.salesforce.wave.util.SFConfig;

public class BulkAPIImpl extends AbstractAPIImpl implements BulkAPI {
	
	private static final Logger LOG = Logger.getLogger(BulkAPIImpl.class);
	
	private Map<String, String> jobContentTypeMap = new LRUCache<String, String>(200);
	private Map<String, BatchInfo> batchedJobMap = new LRUCache<String, BatchInfo>(200);
	private Map<String, BatchInfo> finishedJobMap = new LRUCache<String, BatchInfo>(100);

	public BulkAPIImpl(SFConfig sfConfig) throws Exception {
		super(sfConfig);
	}

	public JobInfo createJob(String object) throws Exception {
		return createUpdateJob(object);
	}

	public JobInfo createUpdateJob(String object) throws Exception {
		JobInfo jobInfo = new JobInfo(STR_CSV, object, STR_UPDATE);

		return createJob(jobInfo);
	}

	public JobInfo createQueryJob(String object) throws Exception {
		return createQueryJob(object, false);
	}

	public JobInfo createQueryJob(String object, Boolean pkChunking) throws Exception {
		return createQueryJob(object,pkChunking, true);
	}

	public JobInfo createQueryJob(String object, Boolean pkChunking, Boolean queryAll) throws Exception {
		return (pkChunking)?
			createQueryJob(object, "Sforce-Enable-PKChunking", "true", queryAll):
			createQueryJob(object, null, null, queryAll);
	}

	public JobInfo createQueryJob(String object, String pkChunkingKey, String pkChunkingValue, Boolean queryAll) throws Exception {
		// TODO fix JSON handling for non-pk chunking requests
		String contentType =  (pkChunkingKey!=null && !"false".equals(pkChunkingValue))?STR_CSV:STR_CSV;  
		JobInfo jobInfo = new JobInfo(contentType, object, queryAll?STR_QUERY_ALL:STR_QUERY);
		jobInfo.setConcurrencyMode(STR_PARALLEL);
		if (pkChunkingKey != null && pkChunkingValue != null)
			jobInfo.setHeader(pkChunkingKey, pkChunkingValue);
 
		return createJob(jobInfo);
	}

	public JobInfo createJob(String object, String operation, String contentType, String concurrencyMode, String header)
			throws Exception {
		JobInfo jobInfo = new JobInfo(contentType, object, operation, header);

		return createJob(jobInfo);
	}

	public JobInfo createJob(String object, String operation, String contentType) throws Exception {
		return createJob(object, operation, contentType, null, null);
	}

	public JobInfo createJob(JobInfo jobInfo) throws Exception {
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getJobPath());

		String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(),
				getObjectMapper().writeValueAsString(jobInfo), true, jobInfo.getHeader());
		LOG.debug("Response from Salesforce Server " + response);

		JobInfo respJobInfo = getObjectMapper().readValue(response.getBytes(), JobInfo.class);
		jobContentTypeMap.put(respJobInfo.getId(), getRespectiveCntType(jobInfo));

		return respJobInfo;
	}

	public BatchInfo addBatch(String jobId, String csvContent) throws Exception {
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId));

		String contentType = getContentType(jobId);
		String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(), csvContent, contentType, true);
		LOG.debug("Response from Salesforce Server " + response);

		// Response is in xml though Accept is set to application/json
		if (CONTENT_TYPE_APPLICATION_JSON.equals(contentType)) {
			return getObjectMapper().readValue(response.getBytes(), BatchInfo.class);
		}

		BatchInfo result = getXmlMapper().readValue(response.getBytes(), BatchInfo.class);
		if (result.isQueued()) {
			batchedJobMap.put(result.getJobId(), result);
		}
		return result;
	}

	public JobInfo closeJob(String jobId, Boolean checkBatchesFirst) throws Exception {
		if (checkBatchesFirst && !this.isCompleted(jobId)) {
			LOG.error("Job " + jobId + " has unprocessed batches. Can not be closed");
			return null;
		}
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getJobPath(jobId));

		JobInfo jobInfo = new JobInfo(STR_CLOSED);
		String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(),
				getObjectMapper().writeValueAsString(jobInfo), true);
		LOG.debug("Response from Salesforce Server " + response);

		return getObjectMapper().readValue(response.getBytes(), JobInfo.class);
	}

	public JobInfo closeJob(String jobId) throws Exception {
		return closeJob(jobId, false);
	}

	public boolean isCompleted(String jobId) throws Exception {
		BatchInfoList batchInfoList = getBatchInfoList(jobId);
		List<BatchInfo> batchInfos = batchInfoList.getBatchInfo();
		boolean isCompleted = true;
		LOG.debug("BatchInfos : " + batchInfos);
		if (batchInfos != null) {
			for (BatchInfo batchInfo : batchInfos) {
				LOG.debug("Batch state : " + batchInfo.getState());
				isCompleted = STR_COMPLETED.equals(batchInfo.getState());
				if (STR_FAILED.equals(batchInfo.getState())) {
					throw new Exception("Batch '" + batchInfo.getId() + "' failed with error '"
							+ batchInfo.getStateMessage() + "'");
				}

				LOG.info("Number of records failed : " + batchInfo.getNumberRecordsFailed());
				if (batchInfo.getNumberRecordsFailed() > 0) {
					String result = getResult(jobId, batchInfo.getId());
					LOG.error("Failed record details \n " + result);
					throw new Exception("Batch '" + batchInfo.getId() + "' failed. Number of failed records is "
							+ batchInfo.getNumberRecordsFailed());
				}
			}
		}

		return isCompleted;
	}

	private String getResult(String jobId, String batchId) throws Exception {
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getBatchResultPath(jobId, batchId));
		return getHttpHelper().get(requestURI, getSfConfig().getSessionId(), true);
	}

	public BatchInfoList getBatchInfoList(String jobId) throws Exception {
	//	Thread.sleep(30000); // thirty seconds
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId));

		String response = getHttpHelper().get(requestURI, getSfConfig().getSessionId(), true);
		LOG.debug("Response from Salesforce Server " + response);

		if (CONTENT_TYPE_APPLICATION_JSON.equals(getContentType(jobId))) {
			return getObjectMapper().readValue(response.getBytes(), BatchInfoList.class);
		}

		return getXmlMapper().readValue(response.getBytes(), BatchInfoList.class);
	}

	public BatchInfo getBatchInfo(String jobId, String batchId) throws Exception {
		PartnerConnection connection = getSfConfig().getPartnerConnection();
		URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId, batchId));

		String response = getHttpHelper().get(requestURI, getSfConfig().getSessionId(), true);
		LOG.debug("Response from Salesforce Server " + response);

		String contentType = getContentType(jobId);

		// Response is in xml though Accept is set to application/json
		if (CONTENT_TYPE_APPLICATION_JSON.equals(contentType)) {
			return getObjectMapper().readValue(response.getBytes(), BatchInfo.class);
		}
		else 
			return  getXmlMapper().readValue(response.getBytes(), BatchInfo.class);
	}

	public boolean isSuccess(String jobId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private String getContentType(String jobId) {
		String contentType = jobContentTypeMap.get(jobId);
		if (StringUtils.isEmpty(contentType)) {
			contentType = CONTENT_TYPE_TEXT_CSV;
		}

		return contentType;
	}

	private String getRespectiveCntType(JobInfo jobInfo) {
		String contentType = null;
		if (STR_JSON.equals(jobInfo.getContentType())) {
			contentType = CONTENT_TYPE_APPLICATION_JSON;
		} else if (STR_XML.equals(jobInfo.getContentType())) {
			contentType = CONTENT_TYPE_APPLICATION_XML;
		} else {
			contentType = CONTENT_TYPE_TEXT_CSV;
		}

		return contentType;
	}

	private String getBatchPath(String jobId, String batchId) {
		StringBuilder batchPath = new StringBuilder();
		batchPath.append(getBatchPath(jobId));
		batchPath.append('/');
		batchPath.append(batchId);
		return batchPath.toString();
	}

	private String getBatchResultPath(String jobId, String batchId) {
		StringBuilder batchResultPath = new StringBuilder();
		batchResultPath.append(getBatchPath(jobId, batchId));
		batchResultPath.append(PATH_RESULT);

		return batchResultPath.toString();
	}

	private String getBatchPath(String jobId) {
		StringBuilder batchPath = new StringBuilder();
		batchPath.append(getJobPath(jobId));
		batchPath.append(PATH_BATCH);

		return batchPath.toString();
	}

	private String getJobPath(String jobId) {
		StringBuilder jobPath = new StringBuilder();
		jobPath.append(getJobPath());
		jobPath.append('/');
		jobPath.append(jobId);
		return jobPath.toString();
	}

	private String getJobPath() {
		StringBuilder jobPath = new StringBuilder();
		jobPath.append(SERVICE_ASYNC_PATH);
		jobPath.append(getSfConfig().getApiVersion());
		jobPath.append(PATH_JOB);
		return jobPath.toString();
	}

	/**
	 * Get list of active jobs
	 * 
	 * @return
	 */
	public List<String> getActiveJobIds() {
		Set<String> jobs = new HashSet<String>(batchedJobMap.size());
		for (BatchInfo batch : batchedJobMap.values()) {
			jobs.add(batch.getJobId());
		}
		return new ArrayList<String>(jobs);
	}

	/**
	 * Get list of recently completed jobs
	 * 
	 * @return
	 */
	public List<String> getCompletedJobIds() {
		Set<String> jobs = new HashSet<String>(finishedJobMap.size());
		for (BatchInfo batch : finishedJobMap.values()) {
			jobs.add(batch.getJobId());
		}
		return new ArrayList<String>(jobs);

	}
	
	/////// Bulk Query
	
    public BatchResult queryBatch(BatchInfo batch) throws Exception {
//    	Thread.sleep(30000); // 30 secs
    	if (!batch.hasDataToLoad()) return null;
        BatchResult result = new BatchResult();
        InputStream is = null;
        try {
    		PartnerConnection connection = getSfConfig().getPartnerConnection();
    		URI requestURI = getSfConfig().getRequestURI(connection, this.getBatchQueryPath(batch, super.getSfConfig()));
            is = getHttpHelper().getQuery(requestURI,
                    getSfConfig().getSessionId(getSfConfig().getPartnerConnection()), getSfConfig().getBatchSize(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            LOG.trace("HEADER:" + line);
            if (line!=null) {
                result.setHeader(Arrays.asList(line.split(",")));
            }            
			while ((line = reader.readLine())!=null) {
				//LOG.trace(line);
				result.addRecord(Arrays.asList(line.split(",")));
			}
        } catch (Exception e) {
            LOG.error("Error while executing salesforce bulk batch query ", e);
            System.exit(-1);
            throw e;
        }
        finally {
        	if (is!=null)
        		is.close();
        }
        return result;   
    }
    

    // callers must close the stream
    public InputStream queryBatchStream(BatchInfo batch) throws Exception {
    	if (!batch.hasDataToLoad()) return null;
        try {
    		PartnerConnection connection = getSfConfig().getPartnerConnection();
    		URI requestURI = getSfConfig().getRequestURI(connection, this.getBatchQueryPath(batch, super.getSfConfig()));
            return getHttpHelper().getQuery(requestURI,
                    getSfConfig().getSessionId(getSfConfig().getPartnerConnection()), getSfConfig().getBatchSize(),true);
        } catch (Exception e) {
            LOG.error("Error while executing salesforce bulk batch query ", e);
            System.exit(-1);
            throw e;
        }
    }

    
    
    private String getBatchQueryPath(BatchInfo batch, SFConfig sfConfig) throws Exception {
        StringBuilder queryPath = new StringBuilder();
    	queryPath.append(this.getBatchResultPath(batch.getJobId(), batch.getId()));
        try {
        	String result = this.getResult(batch.getJobId(), batch.getId()); //<result-list xmlns="http://www.force.com/2009/06/asyncapi/dataload"><result>75236000005tpQ0</result></result-list>
        	Boolean isJson = CONTENT_TYPE_APPLICATION_JSON.equals(getContentType(batch.getJobId()));
        	String pattern = isJson?"[\"[\\s\\S]]":"<result>[\\s\\S]*?<\\/result>"; // TODO fix regex
        	java.util.regex.Pattern r = Pattern.compile(pattern);
        	java.util.regex.Matcher m = r.matcher(result);
        	if (isJson || m.find()) { 
                queryPath.append("/");
        		java.util.regex.MatchResult ff = m.toMatchResult();
        		String toAppend = isJson?result.substring(2, result.length()-2):result.substring(ff.start()+8, ff.end()-9);
            	LOG.trace("Query batch metadata path" + toAppend);
        		queryPath.append(toAppend);
        	} else {
        		BatchInfo b = this.getBatchInfo(batch.getJobId(), batch.getId());
        		// LOG.trace("Actual state: " + b.getState());
        	}
		} catch (Exception e) {
			throw e;
		}
        return queryPath.toString();
    }
    
}

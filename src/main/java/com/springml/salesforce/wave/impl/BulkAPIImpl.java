package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;

import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.api.BulkAPI;
import com.springml.salesforce.wave.model.BatchInfo;
import com.springml.salesforce.wave.model.BatchInfoList;
import com.springml.salesforce.wave.model.JobInfo;
import com.springml.salesforce.wave.util.SFConfig;

public class BulkAPIImpl extends AbstractAPIImpl implements BulkAPI {
    private static final Logger LOG = Logger.getLogger(BulkAPIImpl.class);

    public BulkAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public JobInfo createJob(String object) throws Exception {
        JobInfo jobInfo = new JobInfo(STR_CSV, object, STR_UPDATE);

        return createJob(jobInfo);
    }

    public JobInfo createJob(JobInfo jobInfo) throws Exception {
        PartnerConnection connection = getSfConfig().getPartnerConnection();
        URI requestURI = getSfConfig().getRequestURI(connection, getJobPath());

        String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(),
                getObjectMapper().writeValueAsString(jobInfo), true);
        LOG.debug("Response from Salesforce Server " + response);

        return getObjectMapper().readValue(response.getBytes(), JobInfo.class);
    }

    public BatchInfo addBatch(String jobId, String csvContent) throws Exception {
        PartnerConnection connection = getSfConfig().getPartnerConnection();
        URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId));

        String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(),
                csvContent, CONTENT_TYPE_TEXT_CSV, true);
        LOG.debug("Response from Salesforce Server " + response);

        // Response is in xml though Accept is set to application/json
        return getXmlMapper().readValue(response.getBytes(), BatchInfo.class);
    }

    public JobInfo closeJob(String jobId) throws Exception {
        PartnerConnection connection = getSfConfig().getPartnerConnection();
        URI requestURI = getSfConfig().getRequestURI(connection, getJobPath(jobId));

        JobInfo jobInfo = new JobInfo(STR_CLOSED);
        String response = getHttpHelper().post(requestURI, getSfConfig().getSessionId(),
                getObjectMapper().writeValueAsString(jobInfo), true);
        LOG.debug("Response from Salesforce Server " + response);

        return getObjectMapper().readValue(response.getBytes(), JobInfo.class);
    }

    public boolean isCompleted(String jobId) throws Exception {
        BatchInfoList batchInfoList = getBatchInfoList(jobId);
        List<BatchInfo> batchInfos = batchInfoList.getBatchInfo();
        boolean isCompleted = false;
        if (batchInfos != null) {
            for (BatchInfo batchInfo : batchInfos) {
                isCompleted = STR_COMPLETED.equals(batchInfo.getState());
            }
        }

        return isCompleted;
    }

    public BatchInfoList getBatchInfoList(String jobId) throws Exception {
        PartnerConnection connection = getSfConfig().getPartnerConnection();
        URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId));

        String response = getHttpHelper().get(requestURI, getSfConfig().getSessionId(), true);
        LOG.debug("Response from Salesforce Server " + response);

        return getXmlMapper().readValue(response.getBytes(), BatchInfoList.class);
    }

    public BatchInfo getBatchInfo(String jobId, String batchId) throws Exception {
        PartnerConnection connection = getSfConfig().getPartnerConnection();
        URI requestURI = getSfConfig().getRequestURI(connection, getBatchPath(jobId, batchId));

        String response = getHttpHelper().get(requestURI, getSfConfig().getSessionId(), true);
        LOG.debug("Response from Salesforce Server " + response);

        return getXmlMapper().readValue(response.getBytes(), BatchInfo.class);
    }

    private String getBatchPath(String jobId, String batchId) {
        StringBuilder batchPath = new StringBuilder();
        batchPath.append(getBatchPath(jobId));
        batchPath.append('/');
        batchPath.append(batchId);
        return batchPath.toString();
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
}

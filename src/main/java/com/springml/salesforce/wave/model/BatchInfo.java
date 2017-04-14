package com.springml.salesforce.wave.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springml.salesforce.wave.util.WaveAPIConstants;

@JsonIgnoreProperties
public class BatchInfo implements Serializable {
    private static final long serialVersionUID = 3366976509102917086L;

    private String id;
    private String jobId;
    private String state;
    private String stateMessage;
    private String createdDate;
    private String systemModstamp;
    private long numberRecordsProcessed;
    private long numberRecordsFailed;
    private long totalProcessingTime;
    private long apiActiveProcessingTime;
    private long apexProcessingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getState() {
        return state;
    }
    
    public Boolean isQueued() {
    	return state.equals(WaveAPIConstants.STR_QUEUED);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getSystemModstamp() {
        return systemModstamp;
    }

    public void setSystemModstamp(String systemModstamp) {
        this.systemModstamp = systemModstamp;
    }

    public long getNumberRecordsProcessed() {
        return numberRecordsProcessed;
    }

    public void setNumberRecordsProcessed(long numberRecordsProcessed) {
        this.numberRecordsProcessed = numberRecordsProcessed;
    }

    public long getNumberRecordsFailed() {
        return numberRecordsFailed;
    }

    public void setNumberRecordsFailed(long numberRecordsFailed) {
        this.numberRecordsFailed = numberRecordsFailed;
    }

    public long getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(long totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public long getApiActiveProcessingTime() {
        return apiActiveProcessingTime;
    }

    public void setApiActiveProcessingTime(long apiActiveProcessingTime) {
        this.apiActiveProcessingTime = apiActiveProcessingTime;
    }

    public long getApexProcessingTime() {
        return apexProcessingTime;
    }

    public void setApexProcessingTime(long apexProcessingTime) {
        this.apexProcessingTime = apexProcessingTime;
    }

    public String getStateMessage() {
        return stateMessage;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

}

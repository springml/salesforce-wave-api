package com.springml.salesforce.wave.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class BatchInfo implements Serializable {
    private static final long serialVersionUID = 3366976509102917086L;

    private String id;
    private String jobId;
    private String state;
    private String createdDate;
    private String systemModstamp;
    private String numberRecordsProcessed;
    private String numberRecordsFailed;
    private String totalProcessingTime;
    private String apiActiveProcessingTime;
    private String apexProcessingTime;

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

    public String getNumberRecordsProcessed() {
        return numberRecordsProcessed;
    }

    public void setNumberRecordsProcessed(String numberRecordsProcessed) {
        this.numberRecordsProcessed = numberRecordsProcessed;
    }

    public String getNumberRecordsFailed() {
        return numberRecordsFailed;
    }

    public void setNumberRecordsFailed(String numberRecordsFailed) {
        this.numberRecordsFailed = numberRecordsFailed;
    }

    public String getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(String totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public String getApiActiveProcessingTime() {
        return apiActiveProcessingTime;
    }

    public void setApiActiveProcessingTime(String apiActiveProcessingTime) {
        this.apiActiveProcessingTime = apiActiveProcessingTime;
    }

    public String getApexProcessingTime() {
        return apexProcessingTime;
    }

    public void setApexProcessingTime(String apexProcessingTime) {
        this.apexProcessingTime = apexProcessingTime;
    }

}

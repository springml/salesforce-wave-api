package com.springml.salesforce.wave.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class BatchInfoList {
    @JacksonXmlElementWrapper(useWrapping=false)
    private List<BatchInfo> batchInfo;

    public List<BatchInfo> getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(List<BatchInfo> batchInfo) {
        this.batchInfo = batchInfo;
    }

}

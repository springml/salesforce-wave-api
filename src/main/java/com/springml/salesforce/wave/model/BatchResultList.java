package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class BatchResultList implements Serializable {
    @JacksonXmlElementWrapper(useWrapping=false)
    @JsonProperty("result")
    private List<String> batchResultIds;

    public List<String> getBatchResultIds() {
        return batchResultIds;
    }

    public void setBatchResultIds(List<String> batchResultIds) {
        this.batchResultIds = batchResultIds;
    }
}

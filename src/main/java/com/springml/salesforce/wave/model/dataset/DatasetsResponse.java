package com.springml.salesforce.wave.model.dataset;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "datasets",
    "nextPageUrl",
    "totalSize",
    "url"
})
public class DatasetsResponse {

    @JsonProperty("datasets")
    private List<Dataset> datasets = null;
    @JsonProperty("nextPageUrl")
    private Object nextPageUrl;
    @JsonProperty("totalSize")
    private Integer totalSize;
    @JsonProperty("url")
    private String url;

    @JsonProperty("datasets")
    public List<Dataset> getDatasets() {
        return datasets;
    }

    @JsonProperty("datasets")
    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    @JsonProperty("nextPageUrl")
    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    @JsonProperty("nextPageUrl")
    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    @JsonProperty("totalSize")
    public Integer getTotalSize() {
        return totalSize;
    }

    @JsonProperty("totalSize")
    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

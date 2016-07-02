package com.springml.salesforce.wave.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsertObjectsResponse {
    private boolean hasErrors;
    private String error;
    private List<InsertResult> results;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<InsertResult> getResults() {
        return results;
    }

    public void setResults(List<InsertResult> results) {
        this.results = results;
    }

}

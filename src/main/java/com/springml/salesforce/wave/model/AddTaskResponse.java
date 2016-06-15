package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// For below response
// {"id":"00TB0000003LgMzMAK","success":true,"errors":[]}
// In case of error below response is returned from salesforce
// [{"message":"Related To ID: id value of incorrect type: 006B000002nBrQ","errorCode":"MALFORMED_ID","fields":["WhatId"]}]
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTaskResponse {
    private String id;
    private boolean success;
    private String error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AddTaskResponse [id=" + id + ", success=" + success + ", error=" + error + "]";
    }

}

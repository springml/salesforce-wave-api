package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTaskRequest {
    @JsonProperty("Subject")
    private String subject;
    @JsonProperty("OwnerId")
    private String ownerId;
    @JsonProperty("WhatId")
    private String objId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    @Override
    public String toString() {
        return "AddTaskRequest [subject=" + subject + ", ownerId=" + ownerId + ", objId=" + objId + "]";
    }

}

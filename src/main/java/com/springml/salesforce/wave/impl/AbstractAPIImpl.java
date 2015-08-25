package com.springml.salesforce.wave.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Abstract class to have common methods
 */
public abstract class AbstractAPIImpl {
    private ObjectMapper objectMapper;
    private HTTPHelper httpHelper;
    private SFConfig sfConfig;

    public AbstractAPIImpl(SFConfig sfConfig) throws Exception {
        setHttpHelper(new HTTPHelper());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        setObjectMapper(objectMapper);

        this.sfConfig = sfConfig;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public HTTPHelper getHttpHelper() {
        return httpHelper;
    }

    public void setHttpHelper(HTTPHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public SFConfig getSfConfig() {
        return sfConfig;
    }

    public void setSfConfig(SFConfig sfConfig) {
        this.sfConfig = sfConfig;
    }
}

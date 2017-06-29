package com.springml.salesforce.wave.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.springml.salesforce.wave.util.HTTPHelper;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Abstract class to have common methods
 */
public abstract class AbstractAPIImpl {
    private ObjectMapper objectMapper;
    private XmlMapper xmlMapper;
    private CsvMapper csvMapper;
    private HTTPHelper httpHelper;
    private SFConfig sfConfig;
    private ObjectReader objectReader;

    public AbstractAPIImpl(SFConfig sfConfig) throws Exception {
        setHttpHelper(new HTTPHelper());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        setObjectMapper(objectMapper);

        this.xmlMapper = new XmlMapper();
        
        this.csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder().setColumnSeparator(',').build();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        objectReader = csvMapper.readerFor(String[].class).with(schema);
        
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

    public XmlMapper getXmlMapper() {
        return xmlMapper;
    }

    public void setXmlMapper(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }
    
    public CsvMapper getCsvMapper() {
        return csvMapper;
    }
    
    public ObjectReader getObjectReader() {
        return objectReader;
    }

    public void setObjectReader(ObjectReader reader) {
        this.objectReader=reader;
    }

    public void setCsvMapper(CsvMapper csvMapper) {
        this.csvMapper = csvMapper;
    }

}

package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean class for Salesforce Wave api response
 * { "action": "query",
 * "responseId": "3zgfdv7BUuMLxQVeRhqeek", "results": { "records": [ { "field1":
 * 10, "field2": "Field Value 2", "field23": "Field Value 3" } ] }, "query":
 * "SAQL_Query", "responseTime": 171 }
 */
@JsonIgnoreProperties
public class QueryResult {
    private String responseId;
    private Results results;
    private String query;
    private long responseTime;
    private int limit;
    private int offset;
    private String resultVariable;
    private boolean done;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getResultVariable() {
        return resultVariable;
    }

    public void setResultVariable(String resultVariable) {
        this.resultVariable = resultVariable;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}

package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean class for Salesforce Wave api response { "action": "query",
 * "responseId": "3zgfdv7BUuMLxQVeRhqeek", "results": { "records": [ { "field1":
 * 10, "field2": "Field Value 2", "field23": "Field Value 3" } ] }, "query":
 * "SAQL_Query", "responseTime": 171 }
 */
@JsonIgnoreProperties
public class QueryResult {
	private String responseId;
	private String resultsJson;
	private Results results;
	private String query;
	private long responseTime;

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getResultsJson() {
		return resultsJson;
	}

	public void setResultsJson(String resultsJson) {
		this.resultsJson = resultsJson;
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

}

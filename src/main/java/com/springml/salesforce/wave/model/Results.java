package com.springml.salesforce.wave.model;

import java.util.List;
import java.util.Map;

/**
 * Bean class for Salesforce Wave Query results
 * {
 *   "results": {
 *     "records": [
 *       {
 *         "field1": 11,
 *         "field2": "value",
 *         "field3": "value"
 *       }
 *      ]
 *    }
 *  }
 */
public class Results {
	private List<Map<String, String>> records;

	public List<Map<String, String>> getRecords() {
		return records;
	}

	public void setRecords(List<Map<String, String>> records) {
		this.records = records;
	}
}

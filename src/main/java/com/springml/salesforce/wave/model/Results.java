/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
 * Author  :
 * 	  Samual Alexander, springml
 * Contributors:
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

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

import static com.springml.salesforce.wave.util.WaveAPIConstants.STR_ATTRIBUTES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * POJO for SOQL result
 * {
 *   "totalSize": 4,
 *   "done": true,
 *   "nextRecordsUrl": "/services/data/v34.0/query/01gB000000HupiDIAR-6000",
 *   "records": [
 *     {
 *       "attributes": {
 *         "type": "Opportunity",
 *         "url": "/services/data/v34.0/sobjects/Opportunity/006B0000002ndnuIAA"
 *       },
 *       "AccountId": "001B0000003oYAfIAM",
 *       "Id": "006B0000002ndnuIAA",
 *       "ProposalID__c": "103"
 *     }
 *   ]
 * }
 */
public class SOQLResult {
    private int totalSize;
    private boolean done;
    private String nextRecordsUrl;
    private List<Map<String, Object>> records;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getNextRecordsUrl() {
        return nextRecordsUrl;
    }

    public void setNextRecordsUrl(String nextRecordsUrl) {
        this.nextRecordsUrl = nextRecordsUrl;
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

    @JsonIgnore
    public List<Map<String, String>> filterRecords() {
        List<Map<String, String>> filteredRecords = new ArrayList<Map<String, String>>();
        if (records != null) {
            for (Map<String, Object> fields : records) {
                Map<String, String> filteredMap = new HashMap<String, String>();
                Set<Entry<String, Object>> entries = fields.entrySet();

                for (Entry<String, Object> entry : entries) {
                    if (!entry.getKey().equals(STR_ATTRIBUTES)) {
                        Object value = entry.getValue();
                        if (value instanceof Map) {
                            filteredMap.putAll(getRelatedRecords(entry.getKey(), (Map) value));
                        } else {
                            filteredMap.put(entry.getKey(), String.valueOf(value));
                        }
                    }
                }

                filteredRecords.add(filteredMap);
            }
        }

        return filteredRecords;
    }

    private Map<String, String> getRelatedRecords(String key, Map<String, Object> value) {
        Set<Entry<String, Object>> entries = value.entrySet();

        Map<String, String> relatedRecords = new HashMap<String, String>();

        for (Entry<String, Object> entry : entries) {
            if (!entry.getKey().equals(STR_ATTRIBUTES)) {
                Object childValue = entry.getValue();
                if (childValue instanceof Map) {
                    relatedRecords.putAll(getRelatedRecords(key + "." + entry.getKey(), (Map) childValue));
                } else {
                    relatedRecords.put(key + "." + entry.getKey(), String.valueOf(childValue));
                }
            }
        }

        return relatedRecords;
    }

}

package com.springml.salesforce.wave.model;

import java.util.List;
import java.util.Map;

/**
 * POJO for SOQL result
 * {
 *   "totalSize": 4,
 *   "done": true,
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

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

}

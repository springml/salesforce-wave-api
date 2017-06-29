/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml, oolong
 * Author  :
 *    Kagan Turgut, oolong
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

import java.util.LinkedList;
import java.util.List;

public class BatchResult {

	private List<String> header;
	private List<List<String>> records =  new LinkedList<List<String>>();
	private String fileName;
	private String id;
	private String jobId;
	
    public String toString() {
    	return "BatchResult (job:" + jobId + " batch:" + id + ")";
    }
    
    protected void finalize() throws Throwable {
        try {
            header = null;
            records = null;
        } finally {
            super.finalize();
        }
    }
	
	public BatchResult (String jobId, String id) {
		this.setJobId(jobId);
		this.setId(id);
	}

	public BatchResult (String jobId, String id, String fileName) {
		this(jobId,id);
		this.setFileName(fileName);
	}
	
	public BatchResult () {
	}
	
	public List<List<String>> getRecords() {
		return records;
	}

	public void setRecords(List<List<String>> records) {
		this.records = records;
	}

	public List<String> getHeader() {
		return header;
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}
	
	public void addRecord(List<String> record) {
		records.add(record);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public boolean isEmpty() {
		return header!=null && "Records not found for this query".equals(header.get(0));
	}
}

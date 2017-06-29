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

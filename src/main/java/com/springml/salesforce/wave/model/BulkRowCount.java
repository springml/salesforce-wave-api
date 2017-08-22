package com.springml.salesforce.wave.model;

public class BulkRowCount {

    private int succeeded;
    private int failed;

    public BulkRowCount(int succeeded, int failed) {
        super();
        this.succeeded = succeeded;
        this.failed = failed;
    }

    public int getSucceeded() {
        return succeeded;
    }

    public int getFailed() {
        return failed;
    }

}

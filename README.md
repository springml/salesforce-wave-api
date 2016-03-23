# Salesforce Wave Client Library

A Java client library for [Salesforce Wave REST API] (https://resources.docs.salesforce.com/sfdc/pdf/bi_dev_guide_rest.pdf).

## Features
This library can be used as a Java Client of Salesforce Wave API. 
* It supports querying a dataset using [SAQL] (https://developer.salesforce.com/docs/atlas.en-us.bi_dev_guide_eql.meta/bi_dev_guide_eql/) and return the result as POJO.
* It supports querying saleforce using [SOQL] (https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/) and return the result as POJO.
* It supports updating saleforce object using [REST Bulk API] (https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/)


## Usage
This library requires Salesforce username, Salesforce password and Salesforce loginURL.
* Account provided in Salesforce username should have WAVE API privilege
* Salesforce password should be appended with security token. For example, if a user’s password is mypassword, and the security token is XXXXXXXXXX, the user must provide mypasswordXXXXXXXXXX
* loginURL can be http://login.salesforce.com or http://test.salesforce.com 

### Querying a dataset
This library can be used 
* To query a dataset using [SAQL] (https://developer.salesforce.com/docs/atlas.en-us.bi_dev_guide_eql.meta/bi_dev_guide_eql/). Refer [WaveAPITest.java] (https://github.com/springml/salesforce-wave-api/blob/master/src/test/java/com/springml/salesforce/wave/api/WaveAPITest.java) for querying a dataset from Salesforce Wave
* To query salesforce object using [SOQL] (https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/). Refer [ForceAPITest.java] (https://github.com/springml/salesforce-wave-api/blob/master/src/test/java/com/springml/salesforce/wave/api/ForceAPITest.java) for querying Salesforce object
* To update salesforce object using [REST Bulk API] (https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/). Refer [BulkAPITest.java] (https://github.com/springml/salesforce-wave-api/blob/master/src/test/java/com/springml/salesforce/wave/api/BulkAPITest.java) for updating Salesforce object

### Maven Dependency
```
<dependency>
    <groupId>com.springml</groupId>
    <artifactId>salesforce-wave-api</artifactId>
    <version>1.0.5</version>
</dependency>
```

### Example Usage to query datasets using SAQL
```java
import com.springml.salesforce.wave.api.APIFactory
import com.springml.salesforce.wave.api.WaveAPI;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.model.Results;

WaveAPI waveAPI = APIFactory.getInstance().waveAPI("salesforce_username",
		"salesforce_password_appended_with_security_token", 
        "https://login.salesforce.com");
String saql = "q = load \"dataset_id/dataset_version_id\"; q = group q by ('field1', 'field2'); q = foreach q generate 'field1' as 'field1',  'field2' as 'field2', count() as 'count'; q = limit q 2000;";
QueryResult result = waveAPI.query(saql);
List<Map<String,String>> records = result.getRecords();

```

### Example Usage to query Salesforce objects using SOQL
```java
import com.springml.salesforce.wave.api.APIFactory
import com.springml.salesforce.wave.api.ForceAPI;
import com.springml.salesforce.wave.model.SOQLResult;

ForceAPI forceAPI = APIFactory.getInstance().forceAPI("salesforce_username",
		"salesforce_password_appended_with_security_token", 
        "https://login.salesforce.com");
String soql = "SELECT AccountId, Id FROM Opportunity";
SOQLResult result = forceAPI.query(soql);
List<Map<String,Object>> records = result.getRecords();
// By default Salesforce will return 2000 records in a single call
// To query more use queryMore()
while (!result.isDone()) {
    result = forceAPI.queryMore();
    records.addAll(result.getRecords());
}

```


### Example Usage to update Salesforce objects using Bulk API
```java
import com.springml.salesforce.wave.api.APIFactory
import com.springml.salesforce.wave.api.ForceAPI;
import com.springml.salesforce.wave.model.SOQLResult;

BulkAPI batchAPI = APIFactory.getInstance().bulkAPI("salesforce_username", 
		"salesforce_password_appended_with_security_token",
        "https://login.salesforce.com", "36.0");
// Here we are updating Contact
JobInfo jobInfo = bulkAPI.createJob("Contact");
String jobId = jobInfo.getId();
// CSV Content contains the details to updated
// First column contains the Saleforce Object ID
// And further columns should contain the fields to be updated
// Here Description is updated for two Contacts
String csvContent = "Id,Description\n003B00000067Rnx,SuperMan\n003B00000067Rnw,SpiderMan";
// Like below, all the batches has to be added
// Please note that, csvContent should not exceed 10MB
// Add multiple batches if the content to be updated is more than 10 MB
BatchInfo batch = bulkAPI.addBatch(jobId, csvContent);
// Close the Job after adding all the batches
JobInfo closeJob = bulkAPI.closeJob(jobId);
// bulkAPI.isCompleted(jobId) can be used to check whether the job has been completed
while (!bulkAPI.isCompleted(jobId)) {
    // Waiting till the Job has been completed
    Thread.sleep(500);
}

```
# Salesforce Wave Client Library

A Java client library for [Salesforce Wave REST API] (https://resources.docs.salesforce.com/sfdc/pdf/bi_dev_guide_rest.pdf).

## Features
This library can be used as a Java Client of Salesforce Wave API. Currently it supports querying a dataset using [SAQL] (https://developer.salesforce.com/docs/atlas.en-us.bi_dev_guide_eql.meta/bi_dev_guide_eql/) and return the result as POJO.


## Usage
This library requires Salesforce username, Salesforce password and Salesforce loginURL.
* Account provided in Salesforce username should have WAVE API privilege
* Salesforce password should be appended with security token. For example, if a user’s password is mypassword, and the security token is XXXXXXXXXX, the user must provide mypasswordXXXXXXXXXX
* loginURL can be http://login.salesforce.com or http://test.salesforce.com 

### Querying a dataset
This library can be used to query a dataset using [SAQL] (https://developer.salesforce.com/docs/atlas.en-us.bi_dev_guide_eql.meta/bi_dev_guide_eql/). Refer [WaveAPITest.java] (https://github.com/springml/salesforce-wave-api/blob/master/src/test/java/com/springml/salesforce/wave/api/WaveAPITest.java) for querying a dataset from Salesforce Wave

### Maven Dependency
```
<dependency>
    <groupId>com.springml</groupId>
    <artifactId>salesforce-wave-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Example Usage
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

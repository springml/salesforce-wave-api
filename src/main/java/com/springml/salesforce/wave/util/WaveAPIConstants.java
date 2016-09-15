package com.springml.salesforce.wave.util;

public class WaveAPIConstants {
    public static final String STR_QUERY = "query";
    public static final String STR_UTF_8 = "UTF-8";
    public static final String STR_SPACE = " ";
    public static final String STR_EQUALS = "=";
    public static final String STR_SEMI_COLON = ";";
    public static final String STR_LIMIT_BTW_SPACE = " limit ";
    public static final String STR_OFFSET_BTW_SPACE = " offset ";
    public static final String STR_ATTRIBUTES = "attributes";
    public static final String STR_CSV = "CSV";
    public static final String STR_JSON = "JSON";
    public static final String STR_XML = "XML";
    public static final String STR_INSERT = "insert";
    public static final String STR_UPDATE = "update";
    public static final String STR_CLOSED = "Closed";
    public static final String STR_COMPLETED = "Completed";
    public static final String STR_FAILED = "Failed";

    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE_APPLICATION_XML = "application/xml";
    public static final String CONTENT_TYPE_TEXT_CSV= "text/csv";

    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_X_SFDC_SESSION = "X-SFDC-Session";
    public static final String HEADER_OAUTH = "OAuth ";
    public static final String HEADER_SF_QUERY_OPTIONS = "Sforce-Query-Options";
    public static final String HEADER_BATCH_SIZE = "batchSize=";

    public static final String SERVICE_PATH = "/services/data/";
    public static final String SERVICE_ASYNC_PATH = "/services/async/";
    public static final String API_VERSION = "35.0";
    public static final String PATH_WAVE_QUERY = "/wave/query";
    public static final String PATH_SOAP_ENDPOINT = "/services/Soap/u/" + API_VERSION;
    public static final String SERVICE_PATH_WAVE_QUERY = SERVICE_PATH + "v" + API_VERSION + PATH_WAVE_QUERY;
    public static final String PATH_QUERY = "/query";
    public static final String PATH_SOBJECTS = "/sobjects/";
    public static final String PATH_TASK = "/sobjects/task";
    public static final String PATH_CHATTER = "/chatter";
    public static final String PATH_FEED_ELEMENTS = "/feed-elements";
    public static final String PATH_JOB = "/job";
    public static final String PATH_BATCH = "/batch";
    public static final String PATH_RESULT = "/result";
    public static final String SERVICE_PATH_QUERY = SERVICE_PATH + "v" + API_VERSION + PATH_QUERY;
    public static final String QUERY_PARAM = "q=";

    public static final String SYS_PROPERTY_SOCKET_TIMEOUT = "com.springml.socket.timeout";

    // 10 minutes
    public static final String DEFAULT_CONNECTION_TIMEOUT = "600000";

}

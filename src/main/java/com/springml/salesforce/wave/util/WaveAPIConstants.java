package com.springml.salesforce.wave.util;

public class WaveAPIConstants {
    public static final String STR_QUERY = "query";
    public static final String STR_UTF_8 = "UTF-8";

    public static final String HEADER_APPLICATION_JSON = "application/json";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_OAUTH = "OAuth ";

    public static final int CONNECTION_TIMEOUT = 60000;

    public static final String SERVICE_PATH = "/services/data/";
    public static final String API_VERSION = "35.0";
    public static final String PATH_WAVE_QUERY = "/wave/query";
    public static final String PATH_SOAP_ENDPOINT = "/services/Soap/u/" + API_VERSION;
    public static final String SERVICE_PATH_WAVE_QUERY = SERVICE_PATH + "v" + API_VERSION + PATH_WAVE_QUERY;
    public static final String PATH_QUERY = "/query";
    public static final String SERVICE_PATH_QUERY = SERVICE_PATH + "v" + API_VERSION + PATH_QUERY;
    public static final String QUERY_PARAM = "q=";
}

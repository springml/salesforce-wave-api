package com.springml.salesforce.wave.util;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

/**
 * Helper class for HTTP requests
 */
public class HTTPHelper {
    private static final Logger LOG = Logger.getLogger(HTTPHelper.class);

//    public String post(URI uri, String sessionId, String request) throws Exception {
//        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, false,null);
//    }
//
//    public String post(URI uri, String sessionId, String request, boolean isBulk) throws Exception {
//        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, isBulk,null);
//    }
    
    public String post(URI uri, String sessionId, String request) throws Exception {
        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, false, null);
    }


    public String post(URI uri, String sessionId, String request, boolean isBulk) throws Exception {
        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, isBulk,null);
    }

    
    public String post(URI uri, String sessionId, String request, boolean isBulk, String header) throws Exception {
        return post(uri, sessionId, request, CONTENT_TYPE_APPLICATION_JSON, isBulk,header);
    }

    public String post(URI uri, String sessionId, String request, String contentType, boolean isBulk) throws Exception {
        return post(uri, sessionId, request, contentType, isBulk,null);
    }

    public String post(URI uri, String sessionId, String request, String contentType, boolean isBulk, String header) throws Exception {
        LOG.info("Executing POST request on " + uri);
        LOG.debug("Sending request " + request);
        LOG.debug("Content-Type " + contentType);

        StringEntity entity = new StringEntity(request, STR_UTF_8);
        if (contentType != null) {
            entity.setContentType(contentType);
        } else {
            LOG.debug("As Content-Type is null " + CONTENT_TYPE_APPLICATION_JSON + " Content-Type is used");
            entity.setContentType(CONTENT_TYPE_APPLICATION_JSON);
        }

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(entity);
        httpPost.setConfig(getRequestConfig());
        if (isBulk) {
            httpPost.addHeader(HEADER_X_SFDC_SESSION, sessionId);
            if (header!=null) {
            	String[] params = header.split(":");
            	String headerKey = params[0];
            	String headerValue = params[1];
                httpPost.addHeader(headerKey,headerValue);
            }
        } else {
            httpPost.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
        }

        return execute(uri, httpPost);
    }

    public String get(URI uri, String sessionId, Integer batchSize, boolean isBulk) throws Exception {
        LOG.info("Executing GET request on " + uri);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(getRequestConfig());
        if (isBulk) {
            httpGet.addHeader(HEADER_X_SFDC_SESSION, sessionId);
        } else {
            httpGet.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
            httpGet.addHeader(HEADER_ACCEPT, CONTENT_TYPE_APPLICATION_JSON);
        }

        if (batchSize != null && batchSize != 0) {
            httpGet.addHeader(HEADER_SF_QUERY_OPTIONS, HEADER_BATCH_SIZE + batchSize);
        }

        return execute(uri, httpGet);
    }

    public String get(URI uri, String sessionId, Integer batchSize) throws Exception {
        return get(uri, sessionId, batchSize, false);
    }

    public String get(URI uri, String sessionId, boolean isBulk) throws Exception {
        return get(uri, sessionId, null, isBulk);
    }

    public String get(URI uri, String sessionId) throws Exception {
        return get(uri, sessionId, null);
    }

    private String execute(URI uri, HttpUriRequest httpReq) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        InputStream eis = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpReq);

            int statusCode = response.getStatusLine().getStatusCode();
            if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)) {
                String reasonPhrase = response.getStatusLine().getReasonPhrase();
                String errResponse = IOUtils.toString(response.getEntity().getContent(), STR_UTF_8);
                throw new Exception(
                        String.format("Accessing %s failed. Status %d. Reason %s \n Error from server %s", uri, statusCode, reasonPhrase, errResponse));
            }

            HttpEntity responseEntity = response.getEntity();
            eis = responseEntity.getContent();
            return IOUtils.toString(eis, STR_UTF_8);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                LOG.debug("Error while closing HTTP Client", e);
            }

            try {
                if (eis != null) {
                    eis.close();
                }
            } catch (Exception e) {
                LOG.debug("Error while closing InputStream", e);
            }
        }
    }

    public static RequestConfig getRequestConfig() {
        int timeout = Integer.parseInt(System.getProperty(SYS_PROPERTY_SOCKET_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT));
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();

        return requestConfig;
    }
}

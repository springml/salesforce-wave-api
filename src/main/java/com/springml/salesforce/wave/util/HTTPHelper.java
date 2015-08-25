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

    public String post(URI uri, String sessionId, String request) throws Exception {
        LOG.info("Executing POST request on " + uri);
        LOG.debug("Sending request " + request);

        StringEntity entity = new StringEntity(request, STR_UTF_8);
        entity.setContentType(HEADER_APPLICATION_JSON);

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(entity);
        httpPost.setConfig(getRequestConfig());
        httpPost.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
        return execute(uri, httpPost);
    }

    public String get(URI uri, String sessionId) throws Exception {
        LOG.info("Executing GET request on " + uri);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(getRequestConfig());
        httpGet.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
        httpGet.addHeader(HEADER_ACCEPT, HEADER_APPLICATION_JSON);
        return execute(uri, httpGet);
    }

    public String execute(URI uri, HttpUriRequest httpGet) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        InputStream eis = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)) {
                String reasonPhrase = response.getStatusLine().getReasonPhrase();
                throw new Exception(
                        String.format("Accessing %s failed. Status %d. Reason %s", uri, statusCode, reasonPhrase));
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
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CONNECTION_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT).setConnectionRequestTimeout(CONNECTION_TIMEOUT).build();

        return requestConfig;
    }
}

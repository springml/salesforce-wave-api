package com.springml.salesforce.wave.util;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

/**
 * Helper class for HTTP requests
 */
public class HTTPHelper {
    private static final Logger LOG = Logger.getLogger(HTTPHelper.class);

    public String post(String uri, String sessionId, String request) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        InputStream eis = null;
        try {
            LOG.info("Executing POST request on " + uri);
            HttpPost httpPost = new HttpPost(uri);
            LOG.debug("Sending request " + request);
            StringEntity entity = new StringEntity(request, STR_UTF_8);
            entity.setContentType(HEADER_APPLICATION_JSON);
            httpPost.setConfig(getRequestConfig());
            httpPost.setEntity(entity);
            httpPost.addHeader(HEADER_AUTH, HEADER_OAUTH + sessionId);
            CloseableHttpResponse response = httpClient.execute(httpPost);

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
                LOG.warn("Error while closing HTTP Client", e);
            }

            try {
                if (eis != null) {
                    eis.close();
                }
            } catch (Exception e) {
                LOG.warn("Error while closing InputStream", e);
            }
        }
    }

    public static RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CONNECTION_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT).setConnectionRequestTimeout(CONNECTION_TIMEOUT).build();

        return requestConfig;
    }
}

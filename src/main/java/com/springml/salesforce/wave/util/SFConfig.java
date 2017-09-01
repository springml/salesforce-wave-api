package com.springml.salesforce.wave.util;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SFConfig {
    private static final Logger LOG = Logger.getLogger(SFConfig.class);

    private String username;
    private String password;
    private boolean useAuthToken;
    private String authToken;
    private String salesforceURL;
    private String apiVersion;
    private Integer batchSize;
    private PartnerConnection partnerConnection;
    private Integer maxRetry = 5;

    public SFConfig(String username, String password, String loginURL,
            String apiVersion) {
        this(username, password, loginURL, apiVersion, null);
    }

    public SFConfig(String username, String password, String salesforceURL,
            String apiVersion, Integer batchSize) {
        this.useAuthToken = false;
        this.username = username;
        this.password = password;
        this.salesforceURL = salesforceURL;
        this.apiVersion = apiVersion;
        this.batchSize = batchSize;
    }


    public SFConfig(boolean useAuthToken, String authToken, String loginURL,
                    String apiVersion) {
        this(useAuthToken, authToken, loginURL, apiVersion, null);
    }

    public SFConfig(boolean useAuthToken, String authToken, String salesforceURL,
                    String apiVersion, Integer batchSize) {
        this.useAuthToken = true;
        this.authToken = authToken;
        this.salesforceURL = salesforceURL;
        this.apiVersion = apiVersion;
        this.batchSize = batchSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.useAuthToken = false;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String authToken) {
        this.useAuthToken = true;
        this.authToken = authToken;
    }

    public String getSalesforceURL() {
        return salesforceURL;
    }

    public void setSalesforceURL(String salesforceURL) {
        this.salesforceURL = salesforceURL;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(Integer maxRetry) {
        this.maxRetry = maxRetry;
    }

    public PartnerConnection getPartnerConnection() throws Exception {
        if (partnerConnection == null) {
            partnerConnection = createPartnerConnection();
        }

        return partnerConnection;
    }

    private PartnerConnection createPartnerConnection() throws Exception {
        ConnectorConfig config = new ConnectorConfig();
        if (!useAuthToken) {
            LOG.debug("Connecting SF Partner Connection using " + username);
            config.setUsername(username);
            config.setPassword(password);
            String authEndpoint = getEndpoint(salesforceURL);
            LOG.info("loginURL : " + authEndpoint);
            config.setAuthEndpoint(authEndpoint);
            config.setServiceEndpoint(authEndpoint);

        } else {
            LOG.debug("Connecting SF Partner Connection using authToken");
            String serviceEndpoint = getEndpoint(salesforceURL);
            config.setManualLogin(true);
            config.setSessionId(authToken);
            config.setServiceEndpoint(serviceEndpoint);
        }

        try {
            return Connector.newConnection(config);
        } catch (ConnectionException ce) {
            LOG.error("Exception while creating connection", ce);
            throw new Exception(ce);
        }
    }

    public String getSessionId(PartnerConnection connection) {
        return connection.getConfig().getSessionId();
    }

    public String getSessionId() throws Exception {
        return getPartnerConnection().getConfig().getSessionId();
    }

    public URI getRequestURI(PartnerConnection connection, String path) throws URISyntaxException {
        return getRequestURI(connection, path, null);
    }

    public URI getRequestURI(PartnerConnection connection, String path, String query) throws URISyntaxException {
        URI seURI = new URI(connection.getConfig().getServiceEndpoint());

        return new URI(seURI.getScheme(),seURI.getUserInfo(), seURI.getHost(), seURI.getPort(),
                path, query, null);
    }

    private String getEndpoint(String loginURL) throws Exception {
        URI loginURI = new URI(loginURL);
        return new URI(loginURI.getScheme(), loginURI.getUserInfo(), loginURI.getHost(),
                loginURI.getPort(), PATH_SOAP_ENDPOINT_PRE + apiVersion, null, null).toString();
    }

    public void closeConnection() {
        try {
            if (this.partnerConnection != null) {
                this.partnerConnection.logout();
            }
        } catch (Exception e) {
            // Ignore it
        }

        this.partnerConnection = null;
    }

}

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
    private String loginURL;
    private String apiVersion;
    private Integer batchSize;
    private PartnerConnection partnerConnection;

    public SFConfig(String username, String password, String loginURL,
            String apiVersion) {
        this(username, password, loginURL, apiVersion, null);
    }

    public SFConfig(String username, String password, String loginURL,
            String apiVersion, Integer batchSize) {
        this.username = username;
        this.password = password;
        this.loginURL = loginURL;
        this.apiVersion = apiVersion;
        this.batchSize = batchSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
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

    public PartnerConnection getPartnerConnection() throws Exception {
        if (partnerConnection == null) {
            partnerConnection = createPartnerConnection();
        }

        return partnerConnection;
    }

    private PartnerConnection createPartnerConnection() throws Exception {
        ConnectorConfig config = new ConnectorConfig();
        LOG.debug("Connecting SF Partner Connection using " + username);
        config.setUsername(username);
        config.setPassword(password);
        String authEndpoint = getAuthEndpoint(loginURL);
        LOG.info("loginURL : " + authEndpoint);
        config.setAuthEndpoint(authEndpoint);
        config.setServiceEndpoint(authEndpoint);

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

    public URI getRequestURI(PartnerConnection connection, String path) throws URISyntaxException {
        return getRequestURI(connection, path, null);
    }

    public URI getRequestURI(PartnerConnection connection, String path, String query) throws URISyntaxException {
        URI seURI = new URI(connection.getConfig().getServiceEndpoint());

        return new URI(seURI.getScheme(),seURI.getUserInfo(), seURI.getHost(), seURI.getPort(),
                path, query, null);
    }

    private String getAuthEndpoint(String loginURL) throws Exception {
        URI loginURI = new URI(loginURL);

        return new URI(loginURI.getScheme(), loginURI.getUserInfo(), loginURI.getHost(),
                loginURI.getPort(), PATH_SOAP_ENDPOINT, null, null).toString();
    }

}

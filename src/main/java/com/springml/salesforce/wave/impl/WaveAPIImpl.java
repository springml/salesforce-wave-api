package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectorConfig;
import com.springml.salesforce.wave.api.WaveAPI;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.util.HTTPHelper;

/**
 * Default Implementation for {@link WaveAPI} 
 */
@JsonIgnoreProperties
public class WaveAPIImpl implements WaveAPI {
	private static final Logger LOG = Logger.getLogger(HTTPHelper.class);

	private PartnerConnection connection;
	private String sessionId;
	private ObjectMapper objectMapper;
	private HTTPHelper httpHelper;
	
	public WaveAPIImpl(String username, String password, String loginURL) throws Exception {
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password);
        LOG.info("loginURL : " + getAuthEndpoint(loginURL));
        config.setAuthEndpoint(getAuthEndpoint(loginURL));
        config.setServiceEndpoint(getAuthEndpoint(loginURL));
        
        connection = Connector.newConnection(config);
        sessionId = config.getSessionId();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        httpHelper = new HTTPHelper();
	}
	
	private String getAuthEndpoint(String loginURL) throws Exception {
		URI loginURI = new URI(loginURL);
		
		return new URI(loginURI.getScheme(), loginURI.getUserInfo(), loginURI.getHost(), 
				loginURI.getPort(), PATH_SOAP_ENDPOINT, null, null).toString();
	}

	public QueryResult query(String saql) throws Exception {
		Map<String, String> saqlMap = new HashMap<String, String>(4);
		saqlMap.put(STR_QUERY, saql);
		String request = objectMapper.writeValueAsString(saqlMap);
		
		String response = httpHelper.post(getRequestURL(SERVICE_PATH_QUERY), sessionId, request);
		LOG.debug("Query Response from server " + response);
		return objectMapper.readValue(response.getBytes(), QueryResult.class);
	}

	private String getRequestURL(String path) throws URISyntaxException {
		URI seURI = new URI(connection.getConfig().getServiceEndpoint());
		
		return new URI(seURI.getScheme(),seURI.getUserInfo(), seURI.getHost(), seURI.getPort(), 
				path, null, null).toString();
	}
}

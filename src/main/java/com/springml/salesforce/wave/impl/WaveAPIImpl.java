package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.api.WaveAPI;
import com.springml.salesforce.wave.model.QueryResult;
import com.springml.salesforce.wave.util.SFConfig;

/**
 * Default Implementation for {@link WaveAPI}
 */
@JsonIgnoreProperties
public class WaveAPIImpl extends AbstractAPIImpl implements WaveAPI {
    private static final Logger LOG = Logger.getLogger(WaveAPIImpl.class);

    public WaveAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public QueryResult queryWithPagination(String saql, String resultVar, int pageSize) throws Exception {
        return queryWithPagination(saql, resultVar, pageSize, 0);
    }

    public QueryResult queryMore(QueryResult queryResult) throws Exception {
        if (queryResult.isDone()) {
            throw new Exception("Already all records are read");
        }

        return queryWithPagination(queryResult.getQuery(), queryResult.getResultVariable(),
                queryResult.getLimit(), queryResult.getOffset());
    }

    public QueryResult query(String saql) throws Exception {
        return query(saql, true);
    }

    private QueryResult query(String saql, boolean closeConnection) throws Exception {
        SFConfig sfConfig = getSfConfig();
        PartnerConnection connection = sfConfig.getPartnerConnection();
        try {
            Map<String, String> saqlMap = new HashMap<String, String>(4);
            LOG.info("Query to be executed : " + saql);
            saqlMap.put(STR_QUERY, saql);
            String request = getObjectMapper().writeValueAsString(saqlMap);

            String waveQueryPath = getWaveQueryPath(sfConfig);
            URI queryURI = sfConfig.getRequestURI(connection, waveQueryPath);
            String response = getHttpHelper().post(queryURI, getSfConfig().getSessionId(connection), request);
            LOG.debug("Query Response from server " + response);
            return getObjectMapper().readValue(response.getBytes(), QueryResult.class);
        } finally {
            if (closeConnection) {
                try {
                    closePartnerConnection();
                } catch (Exception e) {
                    LOG.warn("Error while closing PartnerConnection", e);
                }
            }
        }
    }

    private QueryResult queryWithPagination(String saql, String resultVar, int limit, int offset) throws Exception {
        String paginatedSAQL = getPaginatedSAQLQuery(saql, resultVar, limit, offset);
        QueryResult queryResult = query(paginatedSAQL, false);
        if (queryResult.getResults().getRecords().size() < limit) {
            queryResult.setDone(true);
            closePartnerConnection();
        } else {
            queryResult.setQuery(saql);
            queryResult.setLimit(limit);
            queryResult.setOffset(limit + offset);
            queryResult.setResultVariable(resultVar);
            queryResult.setDone(false);
        }

        return queryResult;
    }

    private void closePartnerConnection() {
        try {
            PartnerConnection connection = getSfConfig().getPartnerConnection();
            if (connection != null) {
                connection.logout();
            }
        } catch (Exception e) {
            LOG.warn("Error while closing PartnerConnection", e);
        }
    }

    private String getPaginatedSAQLQuery(String saql, String resultVar, int limit, int offset) throws Exception {
        if (saql.contains("limit") || saql.contains("offset")) {
            throw new Exception("Pagination can't be done for SAQL Query which contains limit|offset");
        }

        StringBuilder paginatedQuery = new StringBuilder();
        paginatedQuery.append(saql);
        paginatedQuery.append(getOffsetClause(resultVar, offset));
        paginatedQuery.append(getLimitClause(resultVar, limit));

        return paginatedQuery.toString();
    }

    private String getOffsetClause(String resultVar, int offset) {
        return getClause(STR_OFFSET_BTW_SPACE, resultVar, offset);
    }

    private String getLimitClause(String resultVar, int limit) {
        return getClause(STR_LIMIT_BTW_SPACE, resultVar, limit);
    }

    private String getClause(String clause, String resultVar, int limit) {
        StringBuilder limitClause = new StringBuilder();
        limitClause.append(resultVar);
        limitClause.append(STR_SPACE);
        limitClause.append(STR_EQUALS);
        limitClause.append(STR_SPACE);
        limitClause.append(clause);
        limitClause.append(resultVar);
        limitClause.append(STR_SPACE);
        limitClause.append(limit);
        limitClause.append(STR_SEMI_COLON);
        limitClause.append(STR_SPACE);

        return limitClause.toString();
    }

    private String getWaveQueryPath(SFConfig sfConfig) {
        StringBuilder waveQueryPath = new StringBuilder();
        waveQueryPath.append(SERVICE_PATH);
        waveQueryPath.append("v");
        waveQueryPath.append(sfConfig.getApiVersion());
        waveQueryPath.append(PATH_WAVE_QUERY);

        return waveQueryPath.toString();
    }

}

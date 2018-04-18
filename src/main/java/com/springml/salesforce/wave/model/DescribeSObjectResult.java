package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DescribeSObjectResult implements Serializable {

    private static final long serialVersionUID = 1739841107381849160L;

    private boolean createable;
    private boolean custom;
    private boolean deletable;
    private List<Field> fields;
    private String name;
    private boolean queryable;
    private boolean replicateable;
    private boolean retrieveable;
    private boolean searchable;
    private boolean undeletable;
    private boolean updateable;
    private String urlDetail;
    private String urlEdit;
    private String urlNew;

    private String error;

    public boolean isCreateable() {
        return createable;
    }

    public void setCreateable(boolean createable) {
        this.createable = createable;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isQueryable() {
        return queryable;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    public boolean isReplicateable() {
        return replicateable;
    }

    public void setReplicateable(boolean replicateable) {
        this.replicateable = replicateable;
    }

    public boolean isRetrieveable() {
        return retrieveable;
    }

    public void setRetrieveable(boolean retrieveable) {
        this.retrieveable = retrieveable;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isUndeletable() {
        return undeletable;
    }

    public void setUndeletable(boolean undeletable) {
        this.undeletable = undeletable;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }

    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
    }

    public String getUrlEdit() {
        return urlEdit;
    }

    public void setUrlEdit(String urlEdit) {
        this.urlEdit = urlEdit;
    }

    public String getUrlNew() {
        return urlNew;
    }

    public void setUrlNew(String urlNew) {
        this.urlNew = urlNew;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

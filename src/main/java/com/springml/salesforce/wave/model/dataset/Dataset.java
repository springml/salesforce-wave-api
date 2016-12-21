package com.springml.salesforce.wave.model.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "createdBy",
    "createdDate",
    "currentVersionCreatedBy",
    "currentVersionCreatedDate",
    "currentVersionId",
    "currentVersionLastModifiedBy",
    "currentVersionLastModifiedDate",
    "currentVersionUrl",
    "folder",
    "id",
    "label",
    "lastAccessedDate",
    "lastModifiedBy",
    "lastModifiedDate",
    "name",
    "permissions",
    "type",
    "url",
    "versionsUrl"
})
public class Dataset {

    @JsonProperty("createdBy")
    private CreatedBy createdBy;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("currentVersionCreatedBy")
    private CurrentVersionCreatedBy currentVersionCreatedBy;
    @JsonProperty("currentVersionCreatedDate")
    private String currentVersionCreatedDate;
    @JsonProperty("currentVersionId")
    private String currentVersionId;
    @JsonProperty("currentVersionLastModifiedBy")
    private CurrentVersionLastModifiedBy currentVersionLastModifiedBy;
    @JsonProperty("currentVersionLastModifiedDate")
    private String currentVersionLastModifiedDate;
    @JsonProperty("currentVersionUrl")
    private String currentVersionUrl;
    @JsonProperty("folder")
    private Folder folder;
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;
    @JsonProperty("lastAccessedDate")
    private String lastAccessedDate;
    @JsonProperty("lastModifiedBy")
    private LastModifiedBy lastModifiedBy;
    @JsonProperty("lastModifiedDate")
    private String lastModifiedDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("permissions")
    private Permissions permissions;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("versionsUrl")
    private String versionsUrl;

    @JsonProperty("createdBy")
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("currentVersionCreatedBy")
    public CurrentVersionCreatedBy getCurrentVersionCreatedBy() {
        return currentVersionCreatedBy;
    }

    @JsonProperty("currentVersionCreatedBy")
    public void setCurrentVersionCreatedBy(CurrentVersionCreatedBy currentVersionCreatedBy) {
        this.currentVersionCreatedBy = currentVersionCreatedBy;
    }

    @JsonProperty("currentVersionCreatedDate")
    public String getCurrentVersionCreatedDate() {
        return currentVersionCreatedDate;
    }

    @JsonProperty("currentVersionCreatedDate")
    public void setCurrentVersionCreatedDate(String currentVersionCreatedDate) {
        this.currentVersionCreatedDate = currentVersionCreatedDate;
    }

    @JsonProperty("currentVersionId")
    public String getCurrentVersionId() {
        return currentVersionId;
    }

    @JsonProperty("currentVersionId")
    public void setCurrentVersionId(String currentVersionId) {
        this.currentVersionId = currentVersionId;
    }

    @JsonProperty("currentVersionLastModifiedBy")
    public CurrentVersionLastModifiedBy getCurrentVersionLastModifiedBy() {
        return currentVersionLastModifiedBy;
    }

    @JsonProperty("currentVersionLastModifiedBy")
    public void setCurrentVersionLastModifiedBy(CurrentVersionLastModifiedBy currentVersionLastModifiedBy) {
        this.currentVersionLastModifiedBy = currentVersionLastModifiedBy;
    }

    @JsonProperty("currentVersionLastModifiedDate")
    public String getCurrentVersionLastModifiedDate() {
        return currentVersionLastModifiedDate;
    }

    @JsonProperty("currentVersionLastModifiedDate")
    public void setCurrentVersionLastModifiedDate(String currentVersionLastModifiedDate) {
        this.currentVersionLastModifiedDate = currentVersionLastModifiedDate;
    }

    @JsonProperty("currentVersionUrl")
    public String getCurrentVersionUrl() {
        return currentVersionUrl;
    }

    @JsonProperty("currentVersionUrl")
    public void setCurrentVersionUrl(String currentVersionUrl) {
        this.currentVersionUrl = currentVersionUrl;
    }

    @JsonProperty("folder")
    public Folder getFolder() {
        return folder;
    }

    @JsonProperty("folder")
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("lastAccessedDate")
    public String getLastAccessedDate() {
        return lastAccessedDate;
    }

    @JsonProperty("lastAccessedDate")
    public void setLastAccessedDate(String lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
    }

    @JsonProperty("lastModifiedBy")
    public LastModifiedBy getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(LastModifiedBy lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @JsonProperty("lastModifiedDate")
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty("lastModifiedDate")
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("permissions")
    public Permissions getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("versionsUrl")
    public String getVersionsUrl() {
        return versionsUrl;
    }

    @JsonProperty("versionsUrl")
    public void setVersionsUrl(String versionsUrl) {
        this.versionsUrl = versionsUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

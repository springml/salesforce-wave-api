
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Photo {

    private String fullEmailPhotoUrl;
    private String largePhotoUrl;
    private Object photoVersionId;
    private String smallPhotoUrl;
    private String standardEmailPhotoUrl;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The fullEmailPhotoUrl
     */
    public String getFullEmailPhotoUrl() {
        return fullEmailPhotoUrl;
    }

    /**
     *
     * @param fullEmailPhotoUrl
     *     The fullEmailPhotoUrl
     */
    public void setFullEmailPhotoUrl(String fullEmailPhotoUrl) {
        this.fullEmailPhotoUrl = fullEmailPhotoUrl;
    }

    /**
     *
     * @return
     *     The largePhotoUrl
     */
    public String getLargePhotoUrl() {
        return largePhotoUrl;
    }

    /**
     *
     * @param largePhotoUrl
     *     The largePhotoUrl
     */
    public void setLargePhotoUrl(String largePhotoUrl) {
        this.largePhotoUrl = largePhotoUrl;
    }

    /**
     *
     * @return
     *     The photoVersionId
     */
    public Object getPhotoVersionId() {
        return photoVersionId;
    }

    /**
     *
     * @param photoVersionId
     *     The photoVersionId
     */
    public void setPhotoVersionId(Object photoVersionId) {
        this.photoVersionId = photoVersionId;
    }

    /**
     *
     * @return
     *     The smallPhotoUrl
     */
    public String getSmallPhotoUrl() {
        return smallPhotoUrl;
    }

    /**
     *
     * @param smallPhotoUrl
     *     The smallPhotoUrl
     */
    public void setSmallPhotoUrl(String smallPhotoUrl) {
        this.smallPhotoUrl = smallPhotoUrl;
    }

    /**
     *
     * @return
     *     The standardEmailPhotoUrl
     */
    public String getStandardEmailPhotoUrl() {
        return standardEmailPhotoUrl;
    }

    /**
     *
     * @param standardEmailPhotoUrl
     *     The standardEmailPhotoUrl
     */
    public void setStandardEmailPhotoUrl(String standardEmailPhotoUrl) {
        this.standardEmailPhotoUrl = standardEmailPhotoUrl;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

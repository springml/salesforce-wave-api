
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Motif {

    private String color;
    private String largeIconUrl;
    private String mediumIconUrl;
    private String smallIconUrl;
    private Object svgIconUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return
     *     The largeIconUrl
     */
    public String getLargeIconUrl() {
        return largeIconUrl;
    }

    /**
     *
     * @param largeIconUrl
     *     The largeIconUrl
     */
    public void setLargeIconUrl(String largeIconUrl) {
        this.largeIconUrl = largeIconUrl;
    }

    /**
     *
     * @return
     *     The mediumIconUrl
     */
    public String getMediumIconUrl() {
        return mediumIconUrl;
    }

    /**
     *
     * @param mediumIconUrl
     *     The mediumIconUrl
     */
    public void setMediumIconUrl(String mediumIconUrl) {
        this.mediumIconUrl = mediumIconUrl;
    }

    /**
     *
     * @return
     *     The smallIconUrl
     */
    public String getSmallIconUrl() {
        return smallIconUrl;
    }

    /**
     *
     * @param smallIconUrl
     *     The smallIconUrl
     */
    public void setSmallIconUrl(String smallIconUrl) {
        this.smallIconUrl = smallIconUrl;
    }

    /**
     *
     * @return
     *     The svgIconUrl
     */
    public Object getSvgIconUrl() {
        return svgIconUrl;
    }

    /**
     *
     * @param svgIconUrl
     *     The svgIconUrl
     */
    public void setSvgIconUrl(Object svgIconUrl) {
        this.svgIconUrl = svgIconUrl;
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


package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClientInfo {

    private String applicationName;
    private Object applicationUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     *
     * @param applicationName
     *     The applicationName
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     *
     * @return
     *     The applicationUrl
     */
    public Object getApplicationUrl() {
        return applicationUrl;
    }

    /**
     *
     * @param applicationUrl
     *     The applicationUrl
     */
    public void setApplicationUrl(Object applicationUrl) {
        this.applicationUrl = applicationUrl;
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

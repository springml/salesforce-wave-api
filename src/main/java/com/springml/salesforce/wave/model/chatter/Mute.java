
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Mute {

    private boolean isMutedByMe;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The isMutedByMe
     */
    public boolean isIsMutedByMe() {
        return isMutedByMe;
    }

    /**
     *
     * @param isMutedByMe
     *     The isMutedByMe
     */
    public void setIsMutedByMe(boolean isMutedByMe) {
        this.isMutedByMe = isMutedByMe;
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


package com.springml.salesforce.wave.model.chatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AssociatedActions {

    private List<Object> platformActionGroups = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The platformActionGroups
     */
    public List<Object> getPlatformActionGroups() {
        return platformActionGroups;
    }

    /**
     *
     * @param platformActionGroups
     *     The platformActionGroups
     */
    public void setPlatformActionGroups(List<Object> platformActionGroups) {
        this.platformActionGroups = platformActionGroups;
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

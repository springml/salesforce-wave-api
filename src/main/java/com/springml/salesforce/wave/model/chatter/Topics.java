
package com.springml.salesforce.wave.model.chatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Topics {

    private boolean canAssignTopics;
    private List<Object> items = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The canAssignTopics
     */
    public boolean isCanAssignTopics() {
        return canAssignTopics;
    }

    /**
     *
     * @param canAssignTopics
     *     The canAssignTopics
     */
    public void setCanAssignTopics(boolean canAssignTopics) {
        this.canAssignTopics = canAssignTopics;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Object> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<Object> items) {
        this.items = items;
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


package com.springml.salesforce.wave.model.chatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Page {

    private String currentPageUrl;
    private List<Object> items = new ArrayList<Object>();
    private Object nextPageUrl;
    private Object previousPageUrl;
    private int total;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The currentPageUrl
     */
    public String getCurrentPageUrl() {
        return currentPageUrl;
    }

    /**
     *
     * @param currentPageUrl
     *     The currentPageUrl
     */
    public void setCurrentPageUrl(String currentPageUrl) {
        this.currentPageUrl = currentPageUrl;
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

    /**
     *
     * @return
     *     The nextPageUrl
     */
    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    /**
     *
     * @param nextPageUrl
     *     The nextPageUrl
     */
    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    /**
     *
     * @return
     *     The previousPageUrl
     */
    public Object getPreviousPageUrl() {
        return previousPageUrl;
    }

    /**
     *
     * @param previousPageUrl
     *     The previousPageUrl
     */
    public void setPreviousPageUrl(Object previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    /**
     *
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
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

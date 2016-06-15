
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Bookmarks {

    private boolean isBookmarkedByCurrentUser;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The isBookmarkedByCurrentUser
     */
    public boolean isIsBookmarkedByCurrentUser() {
        return isBookmarkedByCurrentUser;
    }

    /**
     *
     * @param isBookmarkedByCurrentUser
     *     The isBookmarkedByCurrentUser
     */
    public void setIsBookmarkedByCurrentUser(boolean isBookmarkedByCurrentUser) {
        this.isBookmarkedByCurrentUser = isBookmarkedByCurrentUser;
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

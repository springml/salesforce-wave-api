
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ChatterLikes {

    private boolean isLikedByCurrentUser;
    private Object likesMessage;
    private Object myLike;
    private Page page;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The isLikedByCurrentUser
     */
    public boolean isIsLikedByCurrentUser() {
        return isLikedByCurrentUser;
    }

    /**
     *
     * @param isLikedByCurrentUser
     *     The isLikedByCurrentUser
     */
    public void setIsLikedByCurrentUser(boolean isLikedByCurrentUser) {
        this.isLikedByCurrentUser = isLikedByCurrentUser;
    }

    /**
     *
     * @return
     *     The likesMessage
     */
    public Object getLikesMessage() {
        return likesMessage;
    }

    /**
     *
     * @param likesMessage
     *     The likesMessage
     */
    public void setLikesMessage(Object likesMessage) {
        this.likesMessage = likesMessage;
    }

    /**
     *
     * @return
     *     The myLike
     */
    public Object getMyLike() {
        return myLike;
    }

    /**
     *
     * @param myLike
     *     The myLike
     */
    public void setMyLike(Object myLike) {
        this.myLike = myLike;
    }

    /**
     *
     * @return
     *     The page
     */
    public Page getPage() {
        return page;
    }

    /**
     *
     * @param page
     *     The page
     */
    public void setPage(Page page) {
        this.page = page;
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

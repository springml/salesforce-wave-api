
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Capabilities {

    private AssociatedActions associatedActions;
    private Bookmarks bookmarks;
    private ChatterLikes chatterLikes;
    private Comments comments;
    private Mute mute;
    private Topics topics;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The associatedActions
     */
    public AssociatedActions getAssociatedActions() {
        return associatedActions;
    }

    /**
     *
     * @param associatedActions
     *     The associatedActions
     */
    public void setAssociatedActions(AssociatedActions associatedActions) {
        this.associatedActions = associatedActions;
    }

    /**
     *
     * @return
     *     The bookmarks
     */
    public Bookmarks getBookmarks() {
        return bookmarks;
    }

    /**
     *
     * @param bookmarks
     *     The bookmarks
     */
    public void setBookmarks(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    /**
     *
     * @return
     *     The chatterLikes
     */
    public ChatterLikes getChatterLikes() {
        return chatterLikes;
    }

    /**
     *
     * @param chatterLikes
     *     The chatterLikes
     */
    public void setChatterLikes(ChatterLikes chatterLikes) {
        this.chatterLikes = chatterLikes;
    }

    /**
     *
     * @return
     *     The comments
     */
    public Comments getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     *     The comments
     */
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     *     The mute
     */
    public Mute getMute() {
        return mute;
    }

    /**
     *
     * @param mute
     *     The mute
     */
    public void setMute(Mute mute) {
        this.mute = mute;
    }

    /**
     *
     * @return
     *     The topics
     */
    public Topics getTopics() {
        return topics;
    }

    /**
     *
     * @param topics
     *     The topics
     */
    public void setTopics(Topics topics) {
        this.topics = topics;
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

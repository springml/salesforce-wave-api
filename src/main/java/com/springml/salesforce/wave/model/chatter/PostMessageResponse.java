
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PostMessageResponse {

    private Actor actor;
    private Body body;
    private boolean canShare;
    private Capabilities capabilities;
    private ClientInfo clientInfo;
    private String createdDate;
    private boolean event;
    private String feedElementType;
    private Header header;
    private String id;
    private boolean isDeleteRestricted;
    private String modifiedDate;
    private Object originalFeedItem;
    private Object originalFeedItemActor;
    private Parent parent;
    private String photoUrl;
    private String relativeCreatedDate;
    private String type;
    private String url;
    private String visibility;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     *
     * @param actor
     *     The actor
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     *
     * @return
     *     The body
     */
    public Body getBody() {
        return body;
    }

    /**
     *
     * @param body
     *     The body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     *
     * @return
     *     The canShare
     */
    public boolean isCanShare() {
        return canShare;
    }

    /**
     *
     * @param canShare
     *     The canShare
     */
    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    /**
     *
     * @return
     *     The capabilities
     */
    public Capabilities getCapabilities() {
        return capabilities;
    }

    /**
     *
     * @param capabilities
     *     The capabilities
     */
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    /**
     *
     * @return
     *     The clientInfo
     */
    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    /**
     *
     * @param clientInfo
     *     The clientInfo
     */
    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    /**
     *
     * @return
     *     The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     *     The createdDate
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @return
     *     The event
     */
    public boolean isEvent() {
        return event;
    }

    /**
     *
     * @param event
     *     The event
     */
    public void setEvent(boolean event) {
        this.event = event;
    }

    /**
     *
     * @return
     *     The feedElementType
     */
    public String getFeedElementType() {
        return feedElementType;
    }

    /**
     *
     * @param feedElementType
     *     The feedElementType
     */
    public void setFeedElementType(String feedElementType) {
        this.feedElementType = feedElementType;
    }

    /**
     *
     * @return
     *     The header
     */
    public Header getHeader() {
        return header;
    }

    /**
     *
     * @param header
     *     The header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The isDeleteRestricted
     */
    public boolean isIsDeleteRestricted() {
        return isDeleteRestricted;
    }

    /**
     *
     * @param isDeleteRestricted
     *     The isDeleteRestricted
     */
    public void setIsDeleteRestricted(boolean isDeleteRestricted) {
        this.isDeleteRestricted = isDeleteRestricted;
    }

    /**
     *
     * @return
     *     The modifiedDate
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     *
     * @param modifiedDate
     *     The modifiedDate
     */
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     *
     * @return
     *     The originalFeedItem
     */
    public Object getOriginalFeedItem() {
        return originalFeedItem;
    }

    /**
     *
     * @param originalFeedItem
     *     The originalFeedItem
     */
    public void setOriginalFeedItem(Object originalFeedItem) {
        this.originalFeedItem = originalFeedItem;
    }

    /**
     *
     * @return
     *     The originalFeedItemActor
     */
    public Object getOriginalFeedItemActor() {
        return originalFeedItemActor;
    }

    /**
     *
     * @param originalFeedItemActor
     *     The originalFeedItemActor
     */
    public void setOriginalFeedItemActor(Object originalFeedItemActor) {
        this.originalFeedItemActor = originalFeedItemActor;
    }

    /**
     *
     * @return
     *     The parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     *     The parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     *     The photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     *
     * @param photoUrl
     *     The photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     *
     * @return
     *     The relativeCreatedDate
     */
    public String getRelativeCreatedDate() {
        return relativeCreatedDate;
    }

    /**
     *
     * @param relativeCreatedDate
     *     The relativeCreatedDate
     */
    public void setRelativeCreatedDate(String relativeCreatedDate) {
        this.relativeCreatedDate = relativeCreatedDate;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     *
     * @return
     *     The visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     *     The visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
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

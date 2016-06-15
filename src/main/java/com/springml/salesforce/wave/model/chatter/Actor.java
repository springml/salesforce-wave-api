
package com.springml.salesforce.wave.model.chatter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Actor {

    private String additionalLabel;
    private String communityNickname;
    private String companyName;
    private String displayName;
    private String firstName;
    private String id;
    private boolean isActive;
    private boolean isInThisCommunity;
    private String lastName;
    private Motif motif;
    private Object mySubscription;
    private String name;
    private Photo photo;
    private Object reputation;
    private Object title;
    private String type;
    private String url;
    private String userType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The additionalLabel
     */
    public String getAdditionalLabel() {
        return additionalLabel;
    }

    /**
     *
     * @param additionalLabel
     *     The additionalLabel
     */
    public void setAdditionalLabel(String additionalLabel) {
        this.additionalLabel = additionalLabel;
    }

    /**
     *
     * @return
     *     The communityNickname
     */
    public String getCommunityNickname() {
        return communityNickname;
    }

    /**
     *
     * @param communityNickname
     *     The communityNickname
     */
    public void setCommunityNickname(String communityNickname) {
        this.communityNickname = communityNickname;
    }

    /**
     *
     * @return
     *     The companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName
     *     The companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     *     The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     *     The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     *     The isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     *     The isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     * @return
     *     The isInThisCommunity
     */
    public boolean isIsInThisCommunity() {
        return isInThisCommunity;
    }

    /**
     *
     * @param isInThisCommunity
     *     The isInThisCommunity
     */
    public void setIsInThisCommunity(boolean isInThisCommunity) {
        this.isInThisCommunity = isInThisCommunity;
    }

    /**
     *
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     *     The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     *     The motif
     */
    public Motif getMotif() {
        return motif;
    }

    /**
     *
     * @param motif
     *     The motif
     */
    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    /**
     *
     * @return
     *     The mySubscription
     */
    public Object getMySubscription() {
        return mySubscription;
    }

    /**
     *
     * @param mySubscription
     *     The mySubscription
     */
    public void setMySubscription(Object mySubscription) {
        this.mySubscription = mySubscription;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The reputation
     */
    public Object getReputation() {
        return reputation;
    }

    /**
     *
     * @param reputation
     *     The reputation
     */
    public void setReputation(Object reputation) {
        this.reputation = reputation;
    }

    /**
     *
     * @return
     *     The title
     */
    public Object getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(Object title) {
        this.title = title;
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
     *     The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     *     The userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
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

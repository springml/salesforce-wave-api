
package com.springml.salesforce.wave.model.chatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Header {

    private Object isRichText;
    private List<MessageSegment> messageSegments = new ArrayList<MessageSegment>();
    private String text;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The isRichText
     */
    public Object getIsRichText() {
        return isRichText;
    }

    /**
     *
     * @param isRichText
     *     The isRichText
     */
    public void setIsRichText(Object isRichText) {
        this.isRichText = isRichText;
    }

    /**
     *
     * @return
     *     The messageSegments
     */
    public List<MessageSegment> getMessageSegments() {
        return messageSegments;
    }

    /**
     *
     * @param messageSegments
     *     The messageSegments
     */
    public void setMessageSegments(List<MessageSegment> messageSegments) {
        this.messageSegments = messageSegments;
    }

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
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

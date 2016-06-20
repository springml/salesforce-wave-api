package com.springml.salesforce.wave.model.chatter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MessageBody {
    private List<MessageElement> messageSegments;

    public List<MessageElement> getMessageSegments() {
        return messageSegments;
    }

    public void setMessageSegments(List<MessageElement> messageSegments) {
        this.messageSegments = messageSegments;
    }
}

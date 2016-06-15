package com.springml.salesforce.wave.model.chatter;

import java.util.List;

public class MessageBody {
    private List<MessageElement> messageSegments;

    public List<MessageElement> getMessageSegments() {
        return messageSegments;
    }

    public void setMessageSegments(List<MessageElement> messageSegments) {
        this.messageSegments = messageSegments;
    }
}

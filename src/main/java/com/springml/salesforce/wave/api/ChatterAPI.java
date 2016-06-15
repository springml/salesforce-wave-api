package com.springml.salesforce.wave.api;

import com.springml.salesforce.wave.model.PostMessageRequest;
import com.springml.salesforce.wave.model.chatter.PostMessageResponse;

public interface ChatterAPI {
    public PostMessageResponse postMessage(PostMessageRequest request) throws Exception;

    public PostMessageResponse postMessage(String subjectId, String text,
            String feedElementType) throws Exception;
}

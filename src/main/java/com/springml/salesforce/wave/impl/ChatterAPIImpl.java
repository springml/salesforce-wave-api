package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.util.Arrays;

import com.springml.salesforce.wave.api.ChatterAPI;
import com.springml.salesforce.wave.model.PostMessageRequest;
import com.springml.salesforce.wave.model.chatter.MessageBody;
import com.springml.salesforce.wave.model.chatter.MessageElement;
import com.springml.salesforce.wave.model.chatter.PostMessageResponse;
import com.springml.salesforce.wave.util.SFConfig;

public class ChatterAPIImpl extends AbstractAPIImpl implements ChatterAPI {

    public ChatterAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public PostMessageResponse postMessage(PostMessageRequest request) throws Exception {
        SFConfig sfConfig = getSfConfig();
        String feddElementsPath = getFeedElementsPath(sfConfig);
        URI taskURI = sfConfig.getRequestURI(
                sfConfig.getPartnerConnection(), feddElementsPath);

        String requestStr = getObjectMapper().writeValueAsString(request);
        String responseStr = getHttpHelper().post(taskURI, getSfConfig().getSessionId(), requestStr);

        return getObjectMapper().readValue(responseStr.getBytes(), PostMessageResponse.class);
    }

    public PostMessageResponse postMessage(String subjectId, String text, String feedElementType) throws Exception {
        PostMessageRequest req = new PostMessageRequest();
        req.setSubjectId(subjectId);
        req.setFeedElementType(feedElementType);

        MessageBody postMsgBody = new MessageBody();
        MessageElement messageElement = new MessageElement();
        messageElement.setText(text);
        messageElement.setType("Text");
        postMsgBody.setMessageSegments(Arrays.asList(messageElement));

        req.setBody(postMsgBody);

        return postMessage(req);
    }

    private String getFeedElementsPath(SFConfig sfConfig) {
        StringBuilder feedElementsPath = new StringBuilder();
        feedElementsPath.append(getChatterPath(sfConfig));
        feedElementsPath.append(PATH_FEED_ELEMENTS);

        return feedElementsPath.toString();
    }

    private String getChatterPath(SFConfig sfConfig) {
        StringBuilder chatterPath = new StringBuilder();
        chatterPath.append(SERVICE_PATH);
        chatterPath.append("v");
        chatterPath.append(sfConfig.getApiVersion());
        chatterPath.append(PATH_CHATTER);

        return chatterPath.toString();
    }

}

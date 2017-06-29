/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
 * Contributors  :
 * 	  Samual Alexander, springml
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springml.salesforce.wave.impl;

import static com.springml.salesforce.wave.util.WaveAPIConstants.*;

import java.net.URI;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.springml.salesforce.wave.api.ChatterAPI;
import com.springml.salesforce.wave.model.PostMessageRequest;
import com.springml.salesforce.wave.model.chatter.MessageBody;
import com.springml.salesforce.wave.model.chatter.MessageElement;
import com.springml.salesforce.wave.model.chatter.PostMessageResponse;
import com.springml.salesforce.wave.util.SFConfig;

public class ChatterAPIImpl extends AbstractAPIImpl implements ChatterAPI {
    private static final Logger LOG = Logger.getLogger(ChatterAPIImpl.class);

    public ChatterAPIImpl(SFConfig sfConfig) throws Exception {
        super(sfConfig);
    }

    public PostMessageResponse postMessage(PostMessageRequest request) throws Exception {
        SFConfig sfConfig = getSfConfig();
        String feddElementsPath = getFeedElementsPath(sfConfig);
        URI taskURI = sfConfig.getRequestURI(
                sfConfig.getPartnerConnection(), feddElementsPath);

        String requestStr = getObjectMapper().writeValueAsString(request);
        LOG.debug("Post Message Request " + requestStr);
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

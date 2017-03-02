package com.springml.salesforce.wave.api;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sforce.soap.partner.PartnerConnection;
import com.springml.salesforce.wave.impl.ChatterAPIImpl;
import com.springml.salesforce.wave.model.PostMessageRequest;
import com.springml.salesforce.wave.model.chatter.MessageBody;
import com.springml.salesforce.wave.model.chatter.MessageElement;
import com.springml.salesforce.wave.model.chatter.PostMessageResponse;
import com.springml.salesforce.wave.util.HTTPHelper;

public class ChatterAPITest extends BaseAPITest {
    private static final String SUBJECT_ID = "006B0000002ne2134IAA";
    private static final String FEED_ELEMENT_TYPE = "FeedItem";
    private static final String TEXT = "Great Opportunity";

    @Before
    public void setup() throws Exception {
        super.setup();

        URI uri = new URI("http://dummyhost/services/data/v36.0/chatter/feed-elements");
        when(sfConfig.getRequestURI(any(PartnerConnection.class), anyString())).thenReturn(uri);

        InputStream postMsgJsonIS = this.getClass().getClassLoader().getResourceAsStream("post_message.json");
        String responseJson = IOUtils.toString(postMsgJsonIS, "UTF-8");
        httpHelper = mock(HTTPHelper.class);

        PostMessageRequest req = constructPostMessage();
        String requestStr = objectMapper.writeValueAsString(req);
        when(httpHelper.post(uri, sfConfig.getSessionId(), requestStr)).thenReturn(responseJson);
    }

    @Test
    @Ignore
    public void testRealPostMessage() throws Exception {
        PostMessageRequest req = constructPostMessage();

        ChatterAPI chatterAPI = APIFactory.getInstance().chatterAPI("testacoount@sf.com",
                "PASSWORDSECURITYCODE", "https://login.salesforce.com", "36.0");

        PostMessageResponse postMessage = chatterAPI.postMessage(req);
        validate(postMessage);
    }

    @Test
    public void testPostMessage() throws Exception {
        PostMessageRequest req = constructPostMessage();

        ChatterAPI chatterAPI = APIFactory.getInstance().chatterAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com", "36.0");
        ((ChatterAPIImpl) chatterAPI).setHttpHelper(httpHelper);
        ((ChatterAPIImpl) chatterAPI).setSfConfig(sfConfig);
        ((ChatterAPIImpl) chatterAPI).setObjectMapper(objectMapper);

        PostMessageResponse postMessage = chatterAPI.postMessage(req);
        validate(postMessage);
    }

    @Test
    public void testSimplePostMessage() throws Exception {
        ChatterAPI chatterAPI = APIFactory.getInstance().chatterAPI("dummyusername",
                "dummypassword", "https://login.salesforce.com", "36.0");
        ((ChatterAPIImpl) chatterAPI).setHttpHelper(httpHelper);
        ((ChatterAPIImpl) chatterAPI).setSfConfig(sfConfig);
        ((ChatterAPIImpl) chatterAPI).setObjectMapper(objectMapper);

        PostMessageResponse postMessage = chatterAPI.postMessage(SUBJECT_ID, TEXT, FEED_ELEMENT_TYPE);
        validate(postMessage);
    }

    private void validate(PostMessageResponse postMessage) {
        assertNotNull(postMessage);
        assertEquals(FEED_ELEMENT_TYPE, postMessage.getFeedElementType());
        assertEquals(TEXT, postMessage.getBody().getText());
        assertEquals("Text", postMessage.getBody().getMessageSegments().get(0).getType());
        assertEquals(SUBJECT_ID, postMessage.getActor().getId());
    }

    private PostMessageRequest constructPostMessage() {
        PostMessageRequest req = new PostMessageRequest();
        req.setSubjectId(SUBJECT_ID);
        req.setFeedElementType(FEED_ELEMENT_TYPE);

        MessageBody postMsgBody = new MessageBody();
        MessageElement messageElement = new MessageElement();
        messageElement.setText(TEXT);
        messageElement.setType("Text");
        postMsgBody.setMessageSegments(Arrays.asList(messageElement));

        req.setBody(postMsgBody);
        return req;
    }

}

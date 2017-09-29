package com.afitnerd.magic.model.slack;

import java.util.List;

public class SlackInChannelImageResponse extends SlackResponse {

    public SlackInChannelImageResponse(String imageUrl) {
        getAttachments().add(new Attachment(imageUrl));
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getResponseType() {
        return "in_channel";
    }
}

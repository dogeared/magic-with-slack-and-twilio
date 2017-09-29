package com.afitnerd.magic.model.slack;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SlackInChannelImageResponse extends SlackResponse {

    private List<Attachment> attachments = new ArrayList<>();

    public SlackInChannelImageResponse(String imageUrl) {
        attachments.add(new Attachment(imageUrl));
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String getResponseType() {
        return "in_channel";
    }

    public class Attachment {

        @JsonProperty("image_url")
        private String imageUrl;

        public Attachment(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}

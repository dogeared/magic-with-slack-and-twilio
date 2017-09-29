package com.afitnerd.magic.model.slack;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class SlackResponse {

    private List<Attachment> attachments = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public abstract String getText();

    @JsonProperty("response_type")
    public abstract String getResponseType();


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

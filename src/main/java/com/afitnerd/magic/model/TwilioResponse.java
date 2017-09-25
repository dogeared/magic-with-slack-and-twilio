package com.afitnerd.magic.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Map;

@JacksonXmlRootElement(localName = "Response")
public class TwilioResponse {

    @JacksonXmlProperty(localName = "Message")
    private Message message;

    public TwilioResponse() {
        message = new Message();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public class Message {
        @JacksonXmlProperty(localName = "Body")
        private String body;

        @JacksonXmlProperty(localName = "Media")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String media;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }
    }
}
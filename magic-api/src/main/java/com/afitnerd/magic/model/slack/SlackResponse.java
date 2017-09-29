package com.afitnerd.magic.model.slack;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SlackResponse {

    @JsonProperty("response_type")
    public abstract String getResponseType();
}

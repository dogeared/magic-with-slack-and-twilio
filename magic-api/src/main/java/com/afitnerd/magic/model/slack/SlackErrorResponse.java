package com.afitnerd.magic.model.slack;

public class SlackErrorResponse extends SlackResponse {

    @Override
    public String getText() {
        return "App Error. Please contact your admin.";
    }

    @Override
    public String getResponseType() {
        return "ephemeral";
    }
}

package com.afitnerd.magic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("#{ @environment['base.url'] }")
    protected String baseUrl;

    @Value("#{ @environment['slack.token'] }")
    protected String slackToken;

    public static final String API_PATH = "/api/v1";

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSlackToken() {
        return slackToken;
    }
}

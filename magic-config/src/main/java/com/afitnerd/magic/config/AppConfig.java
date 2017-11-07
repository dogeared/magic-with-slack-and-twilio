package com.afitnerd.magic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class AppConfig {

    @Value("#{ @environment['base.url'] }")
    private String baseUrl;

    @Value("#{ @environment['slack.tokens'] }")
    private String[] slackTokensArray;

    private List<String> slackTokens;

    @PostConstruct
    private void setup() {
        if (slackTokensArray != null) {
            slackTokens = Arrays.asList(slackTokensArray);
        }
    }

    public static final String API_PATH = "/api/v1";

    public String getBaseUrl() {
        return baseUrl;
    }

    public List<String> getSlackTokens() {
        return slackTokens;
    }
}

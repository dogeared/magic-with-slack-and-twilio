package com.afitnerd.magic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("#{ @environment['base.url'] }")
    protected String baseUrl;

    public static final String API_PATH = "/api/v1";

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}

package com.afitnerd.magic.service;

import com.afitnerd.magic.model.slack.SlackResponse;

import java.io.IOException;

public interface SlackResponseService {

    SlackResponse getInChannelResponseWithImage() throws IOException;
    SlackResponse getErrorResponse();
}

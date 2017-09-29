package com.afitnerd.magic.service;

import java.io.IOException;
import java.util.Map;

public interface SlackResponseService {

    Map<String, Object> getInChannelResponseWithImage() throws IOException;
    Map<String, Object> getErrorResponse();
}

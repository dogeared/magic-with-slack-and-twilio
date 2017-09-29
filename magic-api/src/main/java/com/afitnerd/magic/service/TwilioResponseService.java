package com.afitnerd.magic.service;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;

import java.io.IOException;

public interface TwilioResponseService {

    public static final String MAGIC_COMMAND = "magic";

    TwilioResponse getMagicResponse() throws IOException;
    TwilioResponse getErrorResponse();
}

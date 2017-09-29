package com.afitnerd.magic.service;

import com.afitnerd.magic.model.twilio.TwilioResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TwilioResponseServiceImpl implements TwilioResponseService {

    MagicCardProxyService magicCardProxyService;

    public TwilioResponseServiceImpl(MagicCardProxyService magicCardProxyService) {
        this.magicCardProxyService = magicCardProxyService;
    }

    @Override
    public TwilioResponse getMagicResponse() throws IOException {
        TwilioResponse response = new TwilioResponse();
        String imageProxyUrl = magicCardProxyService.getRandomImageProxyUrl();
        response.getMessage().setMedia(imageProxyUrl);
        return response;
    }

    @Override
    public TwilioResponse getErrorResponse() {
        TwilioResponse response = new TwilioResponse();
        response
            .getMessage()
            .setBody("Send\n\n" + MAGIC_COMMAND + "\n\nto get a random Magic the Gathering card sent to you.");
        return response;
    }
}

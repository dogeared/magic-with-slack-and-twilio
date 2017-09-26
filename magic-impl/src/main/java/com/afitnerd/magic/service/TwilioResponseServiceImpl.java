package com.afitnerd.magic.service;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TwilioResponseServiceImpl implements TwilioResponseService {

    MagicCardProxyService magicCardProxyService;

    public TwilioResponseServiceImpl(MagicCardProxyService magicCardProxyService) {
        this.magicCardProxyService = magicCardProxyService;
    }

    @Override
    public TwilioResponse getMagicResponse(TwilioRequest req) throws IOException {
        TwilioResponse response = new TwilioResponse();
        String body = (req.getBody() != null) ? req.getBody().trim().toLowerCase() : "";

        if (!MAGIC_COMMAND.equals(body)) {
            response
                .getMessage()
                .setBody("Send\n\n" + MAGIC_COMMAND + "\n\nto get a random Magic the Gathering card sent to you.");
            return response;
        }

        String imageProxyUrl = magicCardProxyService.getRandomImageProxyUrl();
        response.getMessage().setMedia(imageProxyUrl);
        return response;
    }
}

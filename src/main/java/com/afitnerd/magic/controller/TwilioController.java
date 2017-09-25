package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;
import com.afitnerd.magic.service.MagicCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class TwilioController {

    MagicCardService magicCardService;

    static final String MAGIC_COMMAND = "magic";
    static final String MAGIC_PROXY_PATH = "/magic_proxy";

    ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(TwilioController.class);

    public TwilioController(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @RequestMapping(value = "/twilio", method = RequestMethod.POST, headers = "Accept=application/xml")
    public TwilioResponse twilio(@ModelAttribute TwilioRequest command, HttpServletRequest req) throws IOException {

        log.debug(mapper.writeValueAsString(command));

        TwilioResponse response = new TwilioResponse();
        String body = (command.getBody() != null) ? command.getBody().trim().toLowerCase() : "";

        if (!MAGIC_COMMAND.equals(body)) {
            response
                .getMessage()
                .setBody("Send '" + MAGIC_COMMAND + "', to get a random Magic the Gathering card sent to you.");
            return response;
        }

        StringBuffer requestUrl = req.getRequestURL();
        String imageProxyUrl =
            requestUrl.substring(0, requestUrl.lastIndexOf("/")) +
            MAGIC_PROXY_PATH + "/" +
            magicCardService.getRandomMagicCardImageId();
        response.getMessage().setMedia(imageProxyUrl);
        return response;
    }

    @RequestMapping(value = MAGIC_PROXY_PATH + "/{card_id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] magicProxy(@PathVariable("card_id") String cardId) throws IOException {
        return magicCardService.getRandomMagicCardBytes(cardId);
    }

}

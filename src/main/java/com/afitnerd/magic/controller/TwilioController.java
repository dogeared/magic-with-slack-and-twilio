package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;
import com.afitnerd.magic.service.MagicCardProxyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class TwilioController {

    private MagicCardProxyService magicCardProxyService;

    static final String MAGIC_COMMAND = "magic";

    ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(TwilioController.class);

    public TwilioController(MagicCardProxyService magicCardProxyService) {
        this.magicCardProxyService = magicCardProxyService;
    }

    @RequestMapping(value = "/twilio", method = RequestMethod.POST, headers = "Accept=application/xml", produces=MediaType.APPLICATION_XML_VALUE)
    public TwilioResponse twilio(@ModelAttribute TwilioRequest command, HttpServletRequest req) throws IOException {

        log.debug(mapper.writeValueAsString(command));

        TwilioResponse response = new TwilioResponse();
        String body = (command.getBody() != null) ? command.getBody().trim().toLowerCase() : "";

        if (!MAGIC_COMMAND.equals(body)) {
            response
                .getMessage()
                .setBody("Send\n\n" + MAGIC_COMMAND + "\n\nto get a random Magic the Gathering card sent to you.");
            return response;
        }

        String imageProxyUrl = magicCardProxyService.getRandomImageProxyUrl(req);
        response.getMessage().setMedia(imageProxyUrl);
        return response;
    }
}

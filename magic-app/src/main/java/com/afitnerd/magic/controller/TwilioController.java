package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;
import com.afitnerd.magic.service.TwilioResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.afitnerd.magic.config.AppConfig.API_PATH;

@RestController
@RequestMapping(API_PATH)
public class TwilioController {

    private TwilioResponseService twilioResponseService;

    static final String MAGIC_COMMAND = "magic";

    ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(TwilioController.class);

    public TwilioController(TwilioResponseService twilioResponseService) {
        this.twilioResponseService = twilioResponseService;
    }

    @RequestMapping(value = "/twilio", method = RequestMethod.POST, headers = "Accept=application/xml", produces=MediaType.APPLICATION_XML_VALUE)
    public TwilioResponse twilio(@ModelAttribute TwilioRequest req) throws IOException {

        log.debug(mapper.writeValueAsString(req));

        return twilioResponseService.getMagicResponse(req);
    }
}

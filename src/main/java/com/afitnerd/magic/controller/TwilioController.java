package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.TwilioRequest;
import com.afitnerd.magic.model.TwilioResponse;
import com.afitnerd.magic.service.MagicCardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TwilioController {

    MagicCardService magicCardService;

    @Value("#{ @environment['bitly.access.token'] }")
    protected String bitlyAccessToken;

    ObjectMapper mapper = new ObjectMapper();
    TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};

    static final String BITLY_URL = "https://api-ssl.bitly.com/v3/shorten";
    static final String BITLY_ACCESS_TOKEN_PARAM = "access_token";
    static final String BITLY_LONG_URL_PARAM = "longUrl";

    public TwilioController(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @RequestMapping(value = "/twilio", method = RequestMethod.POST, headers = "Accept=application/xml")
    public TwilioResponse twilio(@ModelAttribute TwilioRequest command) throws IOException {

        String imageUrl = magicCardService.getRandomMagicCardImage();

        // bitly it up
        String bitlyUrl = BITLY_URL + "?" +
            BITLY_ACCESS_TOKEN_PARAM + "=" + bitlyAccessToken + "&" +
            BITLY_LONG_URL_PARAM + "=" + URLEncoder.encode(imageUrl, "UTF-8");
        InputStream is = Request.Get(bitlyUrl)
            .execute()
            .returnResponse()
            .getEntity()
            .getContent();

        Map<String, Object> bitlyResponse = mapper.readValue(is, typeReference);
        String bitly = (String)((Map<String, Object>)bitlyResponse.get("data")).get("url");

        TwilioResponse response = new TwilioResponse();
        response.getMessage().setMedia(bitly);
        return response;
    }
}

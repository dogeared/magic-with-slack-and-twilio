package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.SlackSlashCommand;
import com.afitnerd.magic.service.MagicCardService;
import com.afitnerd.magic.service.SlackResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SlackController {

    private static final Logger log = LoggerFactory.getLogger(SlackController.class);

    MagicCardService magicCardService;
    SlackResponseService slackResponseService;

    public SlackController(MagicCardService magicCardService, SlackResponseService slackResponseService) {
        this.magicCardService = magicCardService;
        this.slackResponseService = slackResponseService;
    }

    @RequestMapping(
        value = "/slack", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    Map<String, Object> slack(@RequestBody SlackSlashCommand slackSlashCommand) throws IOException {

        return slackResponseService.getInChannelResponseWithImage(magicCardService.getRandomMagicCardImage());
    }
}
package com.afitnerd.magic.controller;

import com.afitnerd.magic.config.AppConfig;
import com.afitnerd.magic.model.SlackSlashCommand;
import com.afitnerd.magic.service.MagicCardService;
import com.afitnerd.magic.service.SlackResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.afitnerd.magic.config.AppConfig.API_PATH;

@RestController
@RequestMapping(API_PATH)
public class SlackController {

    private static final Logger log = LoggerFactory.getLogger(SlackController.class);
    private ObjectMapper mapper = new ObjectMapper();

    private AppConfig appConfig;
    private SlackResponseService slackResponseService;

    public SlackController(AppConfig appConfig, SlackResponseService slackResponseService) {
        this.appConfig = appConfig;
        this.slackResponseService = slackResponseService;
    }

    @RequestMapping(
        value = "/slack", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, Object> slack(@RequestBody SlackSlashCommand slackSlashCommand) throws IOException {

        log.debug(mapper.writeValueAsString(slackSlashCommand));

        // check token
        String commandToken = slackSlashCommand.getToken();
        if (slackSlashCommand.getToken() == null || !slackSlashCommand.getToken().equals(appConfig.getSlackToken())) {
            return slackResponseService.getErrorResponse();
        }

        return slackResponseService.getInChannelResponseWithImage();
    }
}
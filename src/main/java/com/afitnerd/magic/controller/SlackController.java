package com.afitnerd.magic.controller;

import com.afitnerd.magic.model.SlackSlashCommand;
import com.afitnerd.magic.service.MagicCardService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SlackController {

    private static final Logger log = LoggerFactory.getLogger(SlackController.class);

    @Autowired
    MagicCardService magicCardService;

    @RequestMapping(
        value = "/slack", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    Map<String, Object> slack(@RequestBody SlackSlashCommand slackSlashCommand) throws IOException {

        Map<String, Object> ret = new HashMap<>();

        List<Map<String, Object>> attachments = new ArrayList<>();
        Map<String, Object> attachment = new HashMap<>();
        attachments.add(attachment);

        attachment.put("image_url", magicCardService.getRandomMagicCardImage());

        ret.put("attachments", attachments);
        return ret;
    }
}
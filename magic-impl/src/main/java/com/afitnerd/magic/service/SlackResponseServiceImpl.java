package com.afitnerd.magic.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlackResponseServiceImpl implements SlackResponseService {

    MagicCardService magicCardService;

    public SlackResponseServiceImpl(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @Override
    public Map<String, Object> getInChannelResponseWithImage() throws IOException {
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("image_url", magicCardService.getRandomMagicCardImageUrl());

        List<Map<String, Object>> attachments = new ArrayList<>();
        attachments.add(attachment);

        Map<String, Object> ret = new HashMap<>();
        ret.put("response_type", "in_channel");
        ret.put("attachments", attachments);
        return ret;
    }

    @Override
    public Map<String, Object> getErrorResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("text", "App Error. Please contact your admin.");
        return response;
    }
}

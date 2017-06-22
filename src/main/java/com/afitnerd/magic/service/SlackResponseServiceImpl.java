package com.afitnerd.magic.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlackResponseServiceImpl implements SlackResponseService {

    @Override
    public Map<String, Object> getInChannelResponseWithImage(String imageUrl) {
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("image_url", imageUrl);

        List<Map<String, Object>> attachments = new ArrayList<>();
        attachments.add(attachment);

        Map<String, Object> ret = new HashMap<>();
        ret.put("response_type", "in_channel");
        ret.put("attachments", attachments);
        return ret;
    }
}

package com.afitnerd.magic.service;

import com.afitnerd.magic.model.slack.SlackErrorResponse;
import com.afitnerd.magic.model.slack.SlackInChannelImageResponse;
import com.afitnerd.magic.model.slack.SlackResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackResponseServiceImpl implements SlackResponseService {

    MagicCardService magicCardService;

    public SlackResponseServiceImpl(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @Override
    public SlackResponse getInChannelResponseWithImage() throws IOException {
        return new SlackInChannelImageResponse(magicCardService.getRandomMagicCardImageUrl());
    }

    @Override
    public SlackResponse getErrorResponse() {
        return new SlackErrorResponse();
    }
}

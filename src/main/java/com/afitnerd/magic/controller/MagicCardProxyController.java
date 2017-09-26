package com.afitnerd.magic.controller;

import com.afitnerd.magic.service.MagicCardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.afitnerd.magic.config.AppConfig.API_PATH;
import static com.afitnerd.magic.service.MagicCardProxyService.MAGIC_PROXY_PATH;

@RestController
@RequestMapping(API_PATH)
public class MagicCardProxyController {

    private MagicCardService magicCardService;

    public MagicCardProxyController(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @RequestMapping(value = MAGIC_PROXY_PATH + "/{card_id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] magicProxy(@PathVariable("card_id") String cardId) throws IOException {
        return magicCardService.getRandomMagicCardBytes(cardId);
    }
}

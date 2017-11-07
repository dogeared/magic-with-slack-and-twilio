package com.afitnerd.magic.controller;

import com.afitnerd.magic.service.MagicCardService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = MAGIC_PROXY_PATH + "/{card_id}")
    public ResponseEntity<byte[]> magicProxy(@PathVariable("card_id") String cardId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        byte[] card = magicCardService.getRandomMagicCardBytes(cardId);

        // need to make sure the content type is set properly - Twilio demands it!
        if (isPNG(card)) {
            headers.setContentType(MediaType.IMAGE_PNG);
        } else {
            headers.setContentType(MediaType.IMAGE_JPEG);
        }
        return new ResponseEntity<>(card, headers, HttpStatus.OK);
    }

    private boolean isPNG(byte[] image) {
        // PNG header, per http://www.libpng.org/pub/png/spec/1.2/PNG-Structure.html
        byte[] expected = { (byte) 137 , 80, 78, 71, 13, 10, 26, 10 };
        if (image == null || image.length < expected.length) { return false; }
        for (int i = 0; i < expected.length; i++) {
            if (image[i] != expected[i]) { return false; }
        }
        return true;
    }
}

package com.afitnerd.magic.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class MagicCardProxyServiceImpl implements MagicCardProxyService {

    private MagicCardService magicCardService;

    public MagicCardProxyServiceImpl(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @Override
    public String getRandomImageProxyUrl(HttpServletRequest req) throws IOException {
        StringBuffer requestUrl = req.getRequestURL();
        String imageProxyUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/")) +
            MAGIC_PROXY_PATH + "/" +
            magicCardService.getRandomMagicCardImageId();
        return imageProxyUrl;
    }
}

package com.afitnerd.magic.service;

import com.afitnerd.magic.config.AppConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.afitnerd.magic.config.AppConfig.API_PATH;

@Service
public class MagicCardProxyServiceImpl implements MagicCardProxyService {

    private AppConfig appConfig;
    private MagicCardService magicCardService;

    public MagicCardProxyServiceImpl(AppConfig appConfig, MagicCardService magicCardService) {
        this.appConfig = appConfig;
        this.magicCardService = magicCardService;
    }

    @Override
    public String getRandomImageProxyUrl() throws IOException {
        String cardId = magicCardService.getRandomMagicCardImageId();
        if (cardId == null) {
            return "/images/maintenance.png";
        }
        return appConfig.getBaseUrl() + AppConfig.API_PATH + MAGIC_PROXY_PATH + "/" + cardId;
    }
}

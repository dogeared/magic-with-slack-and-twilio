package com.afitnerd.magic.service;

import com.afitnerd.magic.config.AppConfig;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MagicCardServiceImpl implements MagicCardService {

    private static final String MAGIC_BASE_URL = "http://gatherer.wizards.com";
    private static final String MAGIC_URL = MAGIC_BASE_URL + "/Pages/Card/Details.aspx?action=random";
    private static final String MAGIC_IMAGE_URL = MAGIC_BASE_URL + "/Handlers/Image.ashx";

    private static final String CARD_ID_PARAM = "multiverseid=";
    private static final int CARD_ID_PARAM_LENGTH = CARD_ID_PARAM.length();

    private static final String IMAGE_HANDLER_PATH = "../../Handlers/Image";

    private AppConfig appConfig;

    public MagicCardServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public String getRandomMagicCardImageUrl() throws IOException {
        return extractImageRef(getPageWithImage());
    }

    @Override
    public String getRandomMagicCardImageId() throws IOException {
        String page = getPageWithImage();
        if (isMGTDown(page)) { return null; }
        String cardUrl = extractImageRef(page);
        int begIdx = cardUrl.indexOf(CARD_ID_PARAM) + CARD_ID_PARAM_LENGTH;
        int endIndex = cardUrl.indexOf("&", begIdx);
        return cardUrl.substring(begIdx, endIndex);
    }

    @Override
    public byte[] getRandomMagicCardBytes(String cardId) throws IOException {
        String cardUrl = MAGIC_IMAGE_URL + "?" + CARD_ID_PARAM + cardId + "&type=card";
        return Request.Get(cardUrl).execute().returnContent().asBytes();
    }

    private String getPageWithImage() throws IOException {
        return Request.Get(MAGIC_URL).execute().returnContent().asString();
    }

    private boolean isMGTDown(String page) {
        return page.indexOf(IMAGE_HANDLER_PATH) < 0;
    }

    private String extractImageRef(String page) throws IOException {
        if (isMGTDown(page)) {
            return appConfig.getBaseUrl() + "/images/maintenance.png";
        }
        int imgBegIndex = page.indexOf("../../Handlers/Image");
        int imgEndIndex = page.indexOf("\" id", imgBegIndex);
        String imgRef = page.substring(imgBegIndex+5, imgEndIndex);
        imgRef = imgRef.replace("&amp;", "&");
        return MAGIC_BASE_URL + imgRef;
    }
}

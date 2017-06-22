package com.afitnerd.magic.service;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MagicCardServiceImpl implements MagicCardService {

    private static final String MAGIC_BASE_URL = "http://gatherer.wizards.com";
    private static final String MAGIC_URL = MAGIC_BASE_URL + "/Pages/Card/Details.aspx?action=random";

    public String getRandomMagicCardImage() throws IOException {
        String page = Request.Get(MAGIC_URL).execute().returnContent().asString();

        int imgBegIndex = page.indexOf("../../Handlers/Image");
        int imgEndIndex = page.indexOf("\" id", imgBegIndex);
        String imgRef = page.substring(imgBegIndex+5, imgEndIndex);
        imgRef = imgRef.replace("&amp;", "&");

        return MAGIC_BASE_URL + imgRef;
    }
}

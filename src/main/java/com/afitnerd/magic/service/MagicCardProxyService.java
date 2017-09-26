package com.afitnerd.magic.service;

import java.io.IOException;

public interface MagicCardProxyService {

    public static final String MAGIC_PROXY_PATH = "/magic_proxy";

    String getRandomImageProxyUrl() throws IOException;
}

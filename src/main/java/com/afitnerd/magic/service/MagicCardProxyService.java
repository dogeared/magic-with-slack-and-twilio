package com.afitnerd.magic.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface MagicCardProxyService {

    public static final String MAGIC_PROXY_PATH = "/magic_proxy";

    String getRandomImageProxyUrl(HttpServletRequest req) throws IOException;
}

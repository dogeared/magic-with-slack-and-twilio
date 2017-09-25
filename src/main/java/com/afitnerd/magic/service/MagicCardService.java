package com.afitnerd.magic.service;

import java.io.IOException;

public interface MagicCardService {

    String getRandomMagicCardImageUrl() throws IOException;
    String getRandomMagicCardImageId() throws IOException;
    byte[] getRandomMagicCardBytes(String cardId) throws IOException;
}

package com.dusklights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DuskLights {
    public static final String MOD_ID = "dusklights";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private DuskLights() {
    }

    public static void init() {
        LOGGER.info("Initializing {}", MOD_ID);
    }
}

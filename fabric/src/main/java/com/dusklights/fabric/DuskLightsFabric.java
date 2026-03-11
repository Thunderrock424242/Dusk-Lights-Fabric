package com.dusklights.fabric;

import com.dusklights.DuskLights;
import net.fabricmc.api.ModInitializer;

public final class DuskLightsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DuskLights.init();
    }
}

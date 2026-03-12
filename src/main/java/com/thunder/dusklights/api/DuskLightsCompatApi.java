package com.thunder.dusklights.api;

import com.thunder.dusklights.DuskLights;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedHashMap;
import java.util.Map;

public final class DuskLightsCompatApi {
    private static final Map<ResourceLocation, DaylightCompatHandler> HANDLERS = new LinkedHashMap<>();

    private DuskLightsCompatApi() {
    }

    /**
     * Registers a custom linked-light compatibility handler.
     *
     * <p>Handlers run in registration order. The first handler that returns {@code true}
     * short-circuits DuskLights' fallback behavior for the current block update.</p>
     *
     * @param id      unique id for this handler
     * @param handler handler implementation
     */
    public static synchronized void registerHandler(ResourceLocation id, DaylightCompatHandler handler) {
        if (HANDLERS.containsKey(id)) {
            throw new IllegalArgumentException("A DuskLights compat handler is already registered for id: " + id);
        }

        HANDLERS.put(id, handler);
        DuskLights.LOGGER.info("Registered DuskLights compat handler: {}", id);
    }

    /**
     * Invoked internally by DuskLights when updating linked blocks.
     */
    public static synchronized boolean applyRegisteredHandlers(ServerLevel level, BlockPos pos, BlockState state, int brightness) {
        for (Map.Entry<ResourceLocation, DaylightCompatHandler> entry : HANDLERS.entrySet()) {
            try {
                if (entry.getValue().apply(level, pos, state, brightness)) {
                    return true;
                }
            } catch (RuntimeException exception) {
                DuskLights.LOGGER.error("DuskLights compat handler {} failed while applying at {}", entry.getKey(), pos, exception);
            }
        }

        return false;
    }
}

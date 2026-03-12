package com.thunder.dusklights.api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface DaylightCompatHandler {
    /**
     * Applies brightness behavior to a linked block.
     *
     * @param level      server level where the block is loaded
     * @param pos        block position of the linked source
     * @param state      current source block state
     * @param brightness dusk brightness value in range 0..15
     * @return {@code true} when the handler applied the change and no fallback logic should run
     */
    boolean apply(ServerLevel level, BlockPos pos, BlockState state, int brightness);
}

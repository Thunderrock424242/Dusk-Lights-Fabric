# DuskLights Mod Compatibility API

This API allows other mods to plug custom light block behavior into DuskLights.

## 1) Mark your block as linkable

Add your block id to the `dusklights:daylight_linkable` block tag.

```json
{
  "replace": false,
  "values": [
    "yourmod:your_light_block"
  ]
}
```

Place that JSON in your mod at:

`data/dusklights/tags/blocks/daylight_linkable.json`

## 2) Register a compatibility handler

If your block needs custom logic (instead of DuskLights' built-in fallback), register a handler during mod initialization.

```java
package com.example.yourmod;

import com.thunder.dusklights.api.DuskLightsCompatApi;
import net.minecraft.resources.ResourceLocation;

public final class YourMod {
    public void onInitialize() {
        DuskLightsCompatApi.registerHandler(
                new ResourceLocation("yourmod", "custom_lamp"),
                (level, pos, state, brightness) -> {
                    if (!state.is(YourBlocks.CUSTOM_LAMP)) {
                        return false; // not ours, let other handlers/default logic run
                    }

                    // Example: map dusk brightness to your own property/state.
                    level.setBlock(
                            pos,
                            state.setValue(CustomLampBlock.POWER, Math.min(4, brightness / 4)),
                            3
                    );

                    return true; // handled
                }
        );
    }
}
```

## Handler behavior

- Handlers execute in registration order.
- Return `true` when your handler fully applied the update.
- Return `false` to let other handlers or DuskLights fallback behavior run.
- Handler ids must be unique (`modid:path`).
- If a handler throws an exception, DuskLights logs it and continues processing.

## Fallback behavior (if no handler handles a block)

DuskLights will attempt one of these defaults:

1. Update an integer property whose name contains `light`, `level`, or `power`.
2. Update a boolean `lit` property.
3. Place/remove an invisible `minecraft:light` block above the source block.

For many simple light blocks, only adding the block tag is enough.

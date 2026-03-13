package com.thunder.dusklights.mixin;

import com.thunder.dusklights.DuskLightsLogic;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {
    @Inject(method = "getLightEmission()I", at = @At("HEAD"), cancellable = true)
    private void dusklights$overrideLinkedLightEmission(CallbackInfoReturnable<Integer> cir) {
        Integer forcedBrightness = DuskLightsLogic.getForcedLightEmissionForLinkedState((BlockState) (Object) this);
        if (forcedBrightness != null) {
            cir.setReturnValue(forcedBrightness);
        }
    }
}

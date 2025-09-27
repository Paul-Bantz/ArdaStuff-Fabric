package com.ardacraft.ardastuff.mixins;

import com.ardacraft.ardastuff.services.WaterSpreadService;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FluidState.class)
public class FluidStateMixin {
    /**
     * Prevents water spreading if the player does not have it enabled.
     * @see com.ardacraft.ardastuff.services.WaterSpreadService
     */
    @Inject(method = "onScheduledTick", at = @At("HEAD"), cancellable = true)
    private void preventFluidSpread(World world, BlockPos pos, CallbackInfo ci) {
        if (!WaterSpreadService.canSpreadAt(pos)) {
            ci.cancel();
        }
    }
}

package com.ardacraft.ardastuff.mixins;

import com.ardacraft.ardastuff.services.MountService;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderPearlEntity.class)
public class EnderPearlEntityMixin {
    /**
     * After the player is teleported by an ender pearl, remove any remaining mounts.
     */
    @Inject(
            method = "onCollision",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;requestTeleportAndDismount(DDD)V",
                    shift = At.Shift.AFTER
            )
    )
    private void afterTeleport(CallbackInfo ci) {
        EnderPearlEntity self = (EnderPearlEntity) (Object) this;
        if (self.getOwner() instanceof ServerPlayerEntity player) {
            MountService.dismount(player);
        }
    }
}

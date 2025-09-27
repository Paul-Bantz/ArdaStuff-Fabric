package com.ardacraft.ardastuff.mixins;

import com.ardacraft.ardastuff.services.MountService;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    /**
     * After a player stops riding an entity, remove the mount.
     */
    @Inject(method = "stopRiding()V", at = @At("TAIL"))
    private void afterStopRiding(CallbackInfo ci, @Local Entity entity) {
        ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
        MountService.dismount(self);
    }
}

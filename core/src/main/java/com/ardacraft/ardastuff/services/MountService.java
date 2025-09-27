package com.ardacraft.ardastuff.services;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MountService {
    private static final Map<UUID, Entity> mounts = new HashMap<>();

    /**
     * Mounts the player on a random horse. If the player is already mounted,
     * they will be dismounted first.
     *
     * @param player The player to mount
     * @return The {@link HorseEntity} instance
     */
    public static HorseEntity mountHorse(ServerPlayerEntity player) {
        dismount(player);

        ServerWorld world = player.getServerWorld();
        Random rand = world.getRandom();
        HorseEntity horse = new HorseEntity(EntityType.HORSE, world);
        HorseColor color = Util.getRandom(HorseColor.values(), rand);

        horse.setTame(true);
        horse.saddle(null);
        horse.setVariant(color);
        horse.teleport(player.getX(), player.getY(), player.getZ());

        world.spawnEntity(horse);
        player.startRiding(horse, true);
        mounts.put(player.getUuid(), horse);

        return horse;
    }

    /**
     * Mounts the player on a boat. If the player is already mounted,
     * they will be dismounted first.
     *
     * @param player The player to mount
     * @return The {@link BoatEntity} instance
     */
    public static BoatEntity mountBoat(ServerPlayerEntity player) {
        dismount(player);

        ServerWorld world = player.getServerWorld();
        BoatEntity boat = new BoatEntity(world, player.getX(), player.getY(), player.getZ());

        boat.setYaw(player.getYaw());

        world.spawnEntity(boat);
        player.startRiding(boat, true);
        mounts.put(player.getUuid(), boat);

        return boat;
    }

    /**
     * Dismounts the player from their current vehicle. If the vehicle is a mount,
     * the mount is discarded.
     *
     * @param player The player to dismount
     */
    public static void dismount(ServerPlayerEntity player) {
        Entity mount = mounts.remove(player.getUuid());
        if (mount != null) {
            if (player.hasVehicle() && player.getVehicle() == mount) {
                player.stopRiding();
            }
            mount.discard();
        }
    }

    /**
     * Checks if the given player has a mount.
     *
     * @param player The player to check
     * @return True if the player has a mount, false otherwise
     */
    public static boolean hasMount(ServerPlayerEntity player) {
        return mounts.containsKey(player.getUuid());
    }

    /**
     * Checks if the given entity is a mount.
     *
     * @param entity The entity to check
     * @return True if the entity is a mount, false otherwise
     */
    public static boolean isMount(Entity entity) {
        return mounts.containsValue(entity);
    }
}

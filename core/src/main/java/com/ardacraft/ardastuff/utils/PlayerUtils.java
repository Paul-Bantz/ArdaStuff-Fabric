package com.ardacraft.ardastuff.utils;

import com.ardacraft.ardastuff.api.Permissions;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerUtils {
    /**
     * Checks if a player has a specific permission.
     *
     * @param player The player to check
     * @param permission The permission to check for
     * @return True if the player has the permission; false otherwise
     */
    public static boolean hasPermission(ServerPlayerEntity player, Permissions permission) {
        return hasPermission(player, permission.getPermission());
    }

    /**
     * Checks if a player has a specific permission.
     *
     * @param player The player to check
     * @param permission The permission to check for
     * @return True if the player has the permission; false otherwise
     */
    public static boolean hasPermission(ServerPlayerEntity player, String permission) {
        return LuckPermsProvider.get()
                .getPlayerAdapter(ServerPlayerEntity.class)
                .getUser(player)
                .getCachedData()
                .getPermissionData()
                .checkPermission(permission)
                .asBoolean();
    }
}

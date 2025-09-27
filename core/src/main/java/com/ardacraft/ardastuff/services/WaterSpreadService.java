package com.ardacraft.ardastuff.services;

import com.ardacraft.ardastuff.mixins.FluidStateMixin;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.fabric.FabricAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.SessionManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;

/**
 * @see FluidStateMixin
 */
public class WaterSpreadService {
    private static final HashSet<String> enabledPlayers = new HashSet<>();

    /**
     * Enables water spreading for the given player.
     *
     * @param player The player to enable water spreading for
     */
    public static void add(ServerPlayerEntity player) {
        enabledPlayers.add(player.getGameProfile().getName());
    }

    /**
     * Disables water spreading for the given player.
     *
     * @param player The player to disable water spreading for
     */
    public static void remove(ServerPlayerEntity player) {
        enabledPlayers.remove(player.getGameProfile().getName());
    }

    /**
     * Checks if water spreading is enabled for the given player.
     *
     * @param player The player to check
     * @return True if water spreading is enabled, false otherwise
     */
    public static boolean isEnabled(ServerPlayerEntity player) {
        return enabledPlayers.contains(player.getGameProfile().getName());
    }

    /**
     * Checks if water can spread at the given block position.
     *
     * @param blockPos The block position to check
     * @return True if water can spread, false otherwise
     */
    public static boolean canSpreadAt(BlockPos blockPos) {
        if (enabledPlayers.isEmpty()) {
            return false;
        }

        SessionManager sessionManager = WorldEdit.getInstance().getSessionManager();
        BlockVector3 pos = FabricAdapter.adapt(blockPos);

        boolean canSpread = false;
        for (String player : enabledPlayers) {
            LocalSession session = sessionManager.findByName(player);
            try {
                if (session != null && session.getSelection().contains(pos)) {
                    canSpread = true;
                    break;
                }
            } catch (IncompleteRegionException ignored) {}
        }

        return canSpread;
    }
}

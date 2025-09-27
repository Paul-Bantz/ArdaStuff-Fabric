package com.ardacraft.ardastuff.commands;

import com.ardacraft.ardastuff.api.Command;
import com.ardacraft.ardastuff.services.MountService;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Mounts the player on a random horse.
 */
public class MountCommand extends Command {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("mount").executes(context -> {
            ServerPlayerEntity player = context.getSource().getPlayer();
            MountService.mountHorse(player);

            return 1;
        }));
    }
}

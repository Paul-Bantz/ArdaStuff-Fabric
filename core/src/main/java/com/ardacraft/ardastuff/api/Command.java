package com.ardacraft.ardastuff.api;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public abstract class Command {
    /**
     * Registers the command with the given dispatcher.
     *
     * @param dispatcher The command dispatcher to register with
     */
    public abstract void register(CommandDispatcher<ServerCommandSource> dispatcher);
}

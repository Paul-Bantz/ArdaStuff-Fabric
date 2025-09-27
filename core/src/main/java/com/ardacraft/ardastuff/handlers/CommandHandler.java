package com.ardacraft.ardastuff.handlers;

import com.ardacraft.ardastuff.api.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandHandler {
    private final Set<Command> commands;

    public CommandHandler() {
        this.commands = new HashSet<>();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            for (Command command : commands) {
                command.register(dispatcher);
            }
        });
    }

    /**
     * Registers a command with the command dispatcher.
     *
     * @param commands The command to register
     */
    public void register(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }
}

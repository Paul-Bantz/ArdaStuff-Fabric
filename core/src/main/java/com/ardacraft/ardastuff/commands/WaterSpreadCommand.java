package com.ardacraft.ardastuff.commands;

import com.ardacraft.ardastuff.api.Command;
import com.ardacraft.ardastuff.api.Permissions;
import com.ardacraft.ardastuff.services.WaterSpreadService;
import com.ardacraft.ardastuff.utils.PlayerUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Toggles water spreading for the player within their WorldEdit selection.
 */
public class WaterSpreadCommand extends Command {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cwaterspread").requires(source -> {
            try {
                return PlayerUtils.hasPermission(source.getPlayerOrThrow(), Permissions.WATER_SPREAD);
            } catch (CommandSyntaxException ignored) {
                return false;
            }
        }).executes(context -> {
            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
            if (WaterSpreadService.isEnabled(player)) {
                WaterSpreadService.remove(player);

                Text msg = Text.literal("Water spreading disabled.").formatted(Formatting.RED);
                player.sendMessage(msg);
            } else {
                WaterSpreadService.add(player);

                Text msg = Text.literal("Water spreading enabled within your WorldEdit selection!").formatted(Formatting.GREEN);
                player.sendMessage(msg);
            }

            return 1;
        }));
    }
}

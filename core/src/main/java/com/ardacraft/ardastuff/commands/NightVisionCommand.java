package com.ardacraft.ardastuff.commands;

import com.ardacraft.ardastuff.api.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Toggles night vision for the player.
 * <p>
 * Usage: /nightvision, /nv
 */
public class NightVisionCommand extends Command {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralCommandNode<ServerCommandSource> node = dispatcher.register(literal("nightvision").executes(context -> {
            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
            if (player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                player.removeStatusEffect(StatusEffects.NIGHT_VISION);

                Text msg = Text.literal("Night vision disabled.").formatted(Formatting.RED);
                player.sendMessage(msg);
            } else {
                StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false, true);
                player.addStatusEffect(effect);

                Text msg = Text.literal("Night vision enabled!").formatted(Formatting.GREEN);
                player.sendMessage(msg);
            }

            return 1;
        }));

        dispatcher.register(literal("nv").redirect(node));
    }
}

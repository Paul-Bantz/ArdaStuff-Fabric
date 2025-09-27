package com.ardacraft.ardastuff.commands;

import com.ardacraft.ardastuff.api.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Gives the player ArdaCraft's guide book.
 * <p>
 * Usage: /guide
 */
public class GuideCommand extends Command {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("guide").executes(context -> {
            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
            ItemStack book = getGuideBook();
            player.giveItemStack(book);

            return 1;
        }));
    }

    /**
     * @return An {@link ItemStack} representing ArdaCraft's guide book
     */
    private ItemStack getGuideBook() {
        ItemStack book = Registries.ITEM.get(Identifier.of("patchouli", "guide_book")).getDefaultStack();
        book.getOrCreateNbt().putString("patchouli:book", "patchouli:ac_guide");
        return book;
    }
}

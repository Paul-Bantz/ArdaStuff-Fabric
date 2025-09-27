package com.ardacraft.ardastuff;

import com.ardacraft.ardastuff.commands.BoatCommand;
import com.ardacraft.ardastuff.commands.GuideCommand;
import com.ardacraft.ardastuff.commands.MountCommand;
import com.ardacraft.ardastuff.commands.NightVisionCommand;
import com.ardacraft.ardastuff.commands.WaterSpreadCommand;
import com.ardacraft.ardastuff.handlers.CommandHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArdaStuff implements DedicatedServerModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ArdaStuff");
    public static final CommandHandler COMMANDS = new CommandHandler();

    @Override
    public void onInitializeServer() {
        COMMANDS.register(
                new BoatCommand(),
                new GuideCommand(),
                new MountCommand(),
                new NightVisionCommand(),
                new WaterSpreadCommand()
        );
    }
}

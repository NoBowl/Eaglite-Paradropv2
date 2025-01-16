package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDropHeightCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public SetDropHeightCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                try {
                    int height = Integer.parseInt(args[0]);
                    plugin.getConfig().set("dropHeight", height);
                    plugin.saveConfig();
                    player.sendMessage("Drop height set to " + height);
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage("Invalid height value.");
                }
            } else {
                player.sendMessage("Usage: /paradrop setheight <height>");
            }
        }
        return false;
    }
}
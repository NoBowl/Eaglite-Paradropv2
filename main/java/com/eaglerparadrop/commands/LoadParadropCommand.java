package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoadParadropCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public LoadParadropCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String name = args[0];
                if (plugin.loadParadropState(name)) {
                    player.sendMessage("Paradrop state " + name + " loaded.");
                    return true;
                } else {
                    player.sendMessage("No saved state found with name " + name);
                }
            } else {
                player.sendMessage("Usage: /paradrop load <name>");
            }
        }
        return false;
    }
}
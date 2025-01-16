package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveParadropCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public SaveParadropCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String name = args[0];
                plugin.saveParadropState(name);
                player.sendMessage("Paradrop state saved as " + name);
                return true;
            } else {
                player.sendMessage("Usage: /paradrop save <name>");
            }
        }
        return false;
    }
}

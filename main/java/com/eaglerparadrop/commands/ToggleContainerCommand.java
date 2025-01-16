package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleContainerCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public ToggleContainerCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Logic to toggle between chests and barrels
            boolean useBarrel = plugin.getConfig().getBoolean("useBarrel", false);
            plugin.getConfig().set("useBarrel", !useBarrel);
            plugin.saveConfig();
            player.sendMessage("Container type toggled to " + (!useBarrel ? "Barrel" : "Chest"));
            return true;
        }
        return false;
    }
}

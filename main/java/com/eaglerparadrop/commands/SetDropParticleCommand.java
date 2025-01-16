package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDropParticleCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public SetDropParticleCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                try {
                    Particle particle = Particle.valueOf(args[0].toUpperCase());
                    plugin.getConfig().set("dropParticle", particle.name());
                    plugin.saveConfig();
                    player.sendMessage("Drop particle set to " + particle.name());
                    return true;
                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid particle type.");
                }
            } else {
                player.sendMessage("Usage: /paradrop setparticle <particle>");
            }
        }
        return false;
    }
}

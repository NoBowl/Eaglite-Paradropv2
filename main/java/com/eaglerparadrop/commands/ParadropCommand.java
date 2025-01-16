package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ParadropCommand implements CommandExecutor {
    private final EaglerParadrop plugin;
    private final Random random = new Random();

    public ParadropCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            World world = player.getWorld();
            
            // Get configured particle type and drop height
            Particle particleType = Particle.valueOf(plugin.getConfig().getString("dropParticle", "CLOUD"));
            int dropHeight = plugin.getConfig().getInt("dropHeight", 20);
            
            // Default block material
            Material blockMaterial = Material.CHEST;
            
            // Check if a custom block material is provided
            if (args.length > 0) {
                try {
                    blockMaterial = Material.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid block material. Using default material.");
                }
            }
            
            // Check if random drop is enabled
            if (plugin.getConfig().getBoolean("randomDropEnabled", false)) {
                int range = plugin.getConfig().getInt("randomDropRange", 1000);
                double randomX = location.getX() + (random.nextDouble() * range * 2) - range;
                double randomZ = location.getZ() + (random.nextDouble() * range * 2) - range;
                location = new Location(world, randomX, world.getHighestBlockYAt((int) randomX, (int) randomZ) + dropHeight, randomZ);
            } else {
                location.add(0, dropHeight, 0);
            }
            
            // Create a falling block to simulate the paradrop
            FallingBlock fallingBlock = world.spawnFallingBlock(location, blockMaterial.createBlockData());
            fallingBlock.setDropItem(false);
            fallingBlock.setVelocity(new Vector(0, -0.5, 0));
            
            // Add animation logic here
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (fallingBlock.isDead() || fallingBlock.isOnGround()) {
                        this.cancel();
                        return;
                    }
                    Location blockLocation = fallingBlock.getLocation();
                    world.spawnParticle(particleType, blockLocation, 10, 0.5, 0.5, 0.5, 0.1);
                }
            }.runTaskTimer(plugin, 0L, 5L);
            
            return true;
        }
        return false;
    }
}

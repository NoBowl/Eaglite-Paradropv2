package com.eaglerparadrop.listeners;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ParadropListener implements Listener {
    private final EaglerParadrop plugin;

    public ParadropListener(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFallingBlockLand(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            Block block = event.getBlock();
            
            // Check if the falling block is a paradrop container
            if (fallingBlock.getBlockData().getMaterial() == Material.CHEST || fallingBlock.getBlockData().getMaterial() == Material.BARREL) {
                // Replace the block with the appropriate container
                boolean useBarrel = plugin.getConfig().getBoolean("useBarrel", false);
                block.setType(useBarrel ? Material.BARREL : Material.CHEST);
                
                // Add items to the container
                Container container = (Container) block.getState();
                Inventory inventory = container.getInventory();
                ItemStack[] items = plugin.getParadropItems();
                inventory.setContents(items);
                
                event.setCancelled(true);
            }
        }
    }
}

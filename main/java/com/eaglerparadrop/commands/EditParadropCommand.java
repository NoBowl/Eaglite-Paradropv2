package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditParadropCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public EditParadropCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory editorInventory = Bukkit.createInventory(null, 27, "Paradrop Editor");
            
            // Create items for editing settings
            ItemStack particleItem = new ItemStack(Material.FIREWORK_STAR);
            ItemMeta particleMeta = particleItem.getItemMeta();
            particleMeta.setDisplayName("Set Particle");
            particleItem.setItemMeta(particleMeta);
            editorInventory.setItem(10, particleItem);
            
            ItemStack heightItem = new ItemStack(Material.FEATHER);
            ItemMeta heightMeta = heightItem.getItemMeta();
            heightMeta.setDisplayName("Set Drop Height");
            heightItem.setItemMeta(heightMeta);
            editorInventory.setItem(12, heightItem);
            
            ItemStack containerItem = new ItemStack(Material.CHEST);
            ItemMeta containerMeta = containerItem.getItemMeta();
            containerMeta.setDisplayName("Toggle Container");
            containerItem.setItemMeta(containerMeta);
            editorInventory.setItem(14, containerItem);
            
            ItemStack lootTableItem = new ItemStack(Material.DIAMOND);
            ItemMeta lootTableMeta = lootTableItem.getItemMeta();
            lootTableMeta.setDisplayName("Edit Loot Table");
            lootTableItem.setItemMeta(lootTableMeta);
            editorInventory.setItem(16, lootTableItem);
            
            player.openInventory(editorInventory);
            return true;
        }
        return false;
    }
}

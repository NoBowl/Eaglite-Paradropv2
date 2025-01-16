package com.eaglerparadrop.commands;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditLootTableCommand implements CommandExecutor {
    private final EaglerParadrop plugin;

    public EditLootTableCommand(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory lootTableInventory = Bukkit.createInventory(null, 54, "Edit Loot Table");
            
            // Populate the inventory with the current loot table items
            ItemStack[] lootItems = plugin.getParadropItems();
            lootTableInventory.setContents(lootItems);
            
            player.openInventory(lootTableInventory);
            return true;
        }
        return false;
    }
}

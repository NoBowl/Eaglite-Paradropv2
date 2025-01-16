package com.eaglerparadrop.listeners;

import com.eaglerparadrop.EaglerParadrop;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ParadropEditorListener implements Listener {
    private final EaglerParadrop plugin;

    public ParadropEditorListener(EaglerParadrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory != null) {
            String title = inventory.getTitle();
            if (title.equals("Paradrop Editor")) {
                event.setCancelled(true);
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null && clickedItem.hasItemMeta()) {
                    String displayName = clickedItem.getItemMeta().getDisplayName();
                    switch (displayName) {
                        case "Set Particle":
                            event.getWhoClicked().sendMessage("Use /psetdropparticle <particle> to set the particle type.");
                            break;
                        case "Set Drop Height":
                            event.getWhoClicked().sendMessage("Use /psetdropheight <height> to set the drop height.");
                            break;
                        case "Toggle Container":
                            boolean useBarrel = plugin.getConfig().getBoolean("useBarrel", false);
                            plugin.getConfig().set("useBarrel", !useBarrel);
                            plugin.saveConfig();
                            event.getWhoClicked().sendMessage("Container type toggled to " + (!useBarrel ? "Barrel" : "Chest"));
                            break;
                        case "Edit Loot Table":
                            event.getWhoClicked().performCommand("editloottable");
                            break;
                    }
                }
            } else if (title.equals("Paradrop States")) {
                event.setCancelled(true);
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null && clickedItem.hasItemMeta()) {
                    String stateName = clickedItem.getItemMeta().getDisplayName();
                    event.getWhoClicked().performCommand("loadparadrop " + stateName);
                }
            } else if (title.equals("Crate Editor")) {
                // Handle crate editor interactions
                // Save the crate items when the inventory is closed
                event.setCancelled(false);
            }
        }
    }
}

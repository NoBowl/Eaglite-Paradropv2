package com.eaglerparadrop;

import com.eaglerparadrop.commands.EditParadropCommand;
import com.eaglerparadrop.commands.ParadropCommand;
import com.eaglerparadrop.commands.ToggleContainerCommand;
import com.eaglerparadrop.listeners.ParadropListener;
import org.bukkit.plugin.java.JavaPlugin;

public class EaglerParadrop extends JavaPlugin {
    @Override
    public void onEnable() {
        // Load configuration
        saveDefaultConfig();
        
        // Register commands
        getCommand("paradrop").setExecutor(new ParadropCommand(this));
        getCommand("paradrop edit").setExecutor(new EditParadropCommand(this));
        getCommand("paradrop container toggle").setExecutor(new ToggleContainerCommand(this));
        getCommand("paradrop setparticle").setExecutor(new SetDropParticleCommand(this));
        getCommand("paradrop setheight").setExecutor(new SetDropHeightCommand(this));
        getCommand("paradrop loottable").setExecutor(new EditLootTableCommand(this));
        getCommand("paradrop save").setExecutor(new SaveParadropCommand(this));
        getCommand("paradrop load").setExecutor(new LoadParadropCommand(this));
        getCommand("paradrop gui").setExecutor(new OpenParadropGUICommand(this));
        getCommand("paradrop editcrate").setExecutor(new EditCrateCommand(this));
        getCommand("paradrop help").setExecutor(new HelpCommand());
        getCommand("paradrop reload").setExecutor(new ReloadCommand(this));
        getCommand("paradrop randomdrop").setExecutor(new RandomDropCommand(this));
        
        // Register events
        getServer().getPluginManager().registerEvents(new ParadropListener(this), this);
        getServer().getPluginManager().registerEvents(new ParadropEditorListener(this), this);
        
        // Credit note
        getLogger().info("Eaglite Paradrop plugin by NoBowl enabled.");
    }

    @Override
    public void onDisable() {
        // Credit note
        getLogger().info("Eaglite Paradrop plugin by NoBowl disabled.");
    }

    public void saveParadropState(String name) {
        // Logic to save the current paradrop state
        getConfig().set("savedStates." + name + ".dropParticle", getConfig().getString("dropParticle"));
        getConfig().set("savedStates." + name + ".dropHeight", getConfig().getInt("dropHeight"));
        getConfig().set("savedStates." + name + ".useBarrel", getConfig().getBoolean("useBarrel"));
        // Save loot table items
        ItemStack[] items = getParadropItems();
        getConfig().set("savedStates." + name + ".lootTable", items);
        saveConfig();
    }

    public boolean loadParadropState(String name) {
        // Logic to load a saved paradrop state
        if (getConfig().contains("savedStates." + name)) {
            getConfig().set("dropParticle", getConfig().getString("savedStates." + name + ".dropParticle"));
            getConfig().set("dropHeight", getConfig().getInt("savedStates." + name + ".dropHeight"));
            getConfig().set("useBarrel", getConfig().getBoolean("savedStates." + name + ".useBarrel"));
            // Load loot table items
            ItemStack[] items = (ItemStack[]) getConfig().get("savedStates." + name + ".lootTable");
            setParadropItems(items);
            saveConfig();
            return true;
        }
        return false;
    }

    public ItemStack[] getParadropItems() {
        // Logic to get the current paradrop items
        // ...existing code...
    }

    public void setParadropItems(ItemStack[] items) {
        // Logic to set the current paradrop items
        // ...existing code...
    }
}

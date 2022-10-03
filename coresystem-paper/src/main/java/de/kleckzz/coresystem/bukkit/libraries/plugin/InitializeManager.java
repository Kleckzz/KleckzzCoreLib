package de.kleckzz.coresystem.bukkit.libraries.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

/**
 * @author KeksGauner
 */
public class InitializeManager {

    /**
     * Register a Command to Bukkit
     * @param plugin this is the plugin instance
     * @param register the class to register the command
     */
    @SuppressWarnings("unused")
    public static void registerCommand(Plugin plugin, Object register) {
        try {
            final Field bukkitCommandMap = plugin.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            String prefix = plugin.getDescription().getName();
            if(prefix == null) {
                throw new NullPointerException();
            }
            commandMap.register(prefix, (Command) register);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register a Event to Bukkit
     * @param plugin this is the plugin instance
     * @param register the class to register the Event
     */
    public static void registerEvent(Plugin plugin, Listener register) {
        try {
            plugin.getServer().getPluginManager().registerEvents(register, plugin);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

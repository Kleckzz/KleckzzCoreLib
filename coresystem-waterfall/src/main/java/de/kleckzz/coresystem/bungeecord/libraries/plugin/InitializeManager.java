package de.kleckzz.coresystem.bungeecord.libraries.plugin;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author KeksGauner
 */
public class InitializeManager {

    /**
     * Register a Command
     * @param plugin Require the ProxyPlugin
     * @param register Require the command class
     */
    public static void registerCommand(Plugin plugin, Command register) {
        try {
            ProxyServer.getInstance().getPluginManager().registerCommand(plugin, register);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Regiter a Event
     * @param plugin Require the ProxyPlugin
     * @param register Require the class of the listener
     */
    public static void registerEvent(Plugin plugin, Listener register) {
        try {
            ProxyServer.getInstance().getPluginManager().registerListener(plugin, register);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

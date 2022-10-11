/*
 * Copyright 2022 Paul Rakutt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
@SuppressWarnings("unused")
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

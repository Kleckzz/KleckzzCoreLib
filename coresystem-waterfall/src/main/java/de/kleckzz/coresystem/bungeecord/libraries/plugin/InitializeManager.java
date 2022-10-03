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
package de.kleckzz.coresystem.bungeecord.libraries.plugin;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author KeksGauner
 */
@SuppressWarnings("unused")
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
     * Register a Event
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

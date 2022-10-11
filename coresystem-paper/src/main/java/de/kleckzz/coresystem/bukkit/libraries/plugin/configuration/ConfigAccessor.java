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
package de.kleckzz.coresystem.bukkit.libraries.plugin.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * @author KeksGauner
 */
@SuppressWarnings("unused")
public class ConfigAccessor {

    private final String fileName;
    private final JavaPlugin plugin;

    private final File configFile;
    private FileConfiguration fileConfiguration;

    /**
     * Create a instance to access a file
     * @param plugin Require the ProxyPlugin
     * @param fileName Require the name of the file
     */
    public ConfigAccessor(JavaPlugin plugin, String fileName) {
        if (plugin == null)
            throw new IllegalArgumentException("plugin cannot be null");
        this.plugin = plugin;
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), fileName);
    }

    /**
     * Reload the config
     */
    public void reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            fileConfiguration.setDefaults(defConfig);
        }
    }


    /**
     * A way to access the config
     * @return the FileConfiguration
     */
    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }

    /**
     * A way to save the config
     */
    public void saveConfig() {
        if (fileConfiguration != null && configFile != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        } else
            plugin.getLogger().log(Level.INFO, "Could not save config to " + configFile, getConfig());
    }

    /**
     * Copy the config to the folder if there is no config
     */
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }

}
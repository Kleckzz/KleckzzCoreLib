package de.kleckzz.coresystem.bungeecord.libraries.plugin.configuration;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.logging.Level;

/**
 * @author KeksGauner
 */
public class ConfigAccessor {

    private final String fileName;
    private final Plugin plugin;

    private final File configFile;
    private Configuration fileConfiguration;

    /**
     * Create a instance to access a file
     * @param plugin Require the ProxyPlugin
     * @param fileName Require the name of the file
     */
    public ConfigAccessor(Plugin plugin, String fileName) {
        if (plugin == null)
            throw new IllegalArgumentException("plugin cannot be null");
        this.plugin = plugin;
        this.fileName = fileName;
        if (!plugin.getDataFolder().exists()) {
            if(plugin.getDataFolder().mkdir()) {
                plugin.getLogger().log(Level.INFO, "Folder successful created! " + plugin.getDataFolder().getPath());
            }
        }
        this.configFile = new File(plugin.getDataFolder(), fileName);
    }

    /**
     * Reload the config. This is not recommend
     */
    public void reloadConfig() {
        try {
            fileConfiguration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResourceAsStream(fileName);
        if (defConfigStream != null) {
            Configuration defConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new InputStreamReader(defConfigStream));
            for(String key : defConfig.getKeys()) {
                if(!fileConfiguration.contains(key)) {
                    plugin.getLogger().log(Level.SEVERE, "Could not read config \"" + configFile + "\" check key \"" + key + "\"! I'll write this into the config. Is this don't work did you try delete the config?", defConfig);
                    if(key == "version") {
                        fileConfiguration.set(key, 0);
                    } else {
                        fileConfiguration.set(key, defConfig.get(key));
                    }
                    saveConfig();
                }
            }
        }
    }

    /**
     * A way to access the config
     * @return the Configuration
     */
    public Configuration getConfig() {
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
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(getConfig(), configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }

    /**
     * Copy the config to the folder if there is no config
     */
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
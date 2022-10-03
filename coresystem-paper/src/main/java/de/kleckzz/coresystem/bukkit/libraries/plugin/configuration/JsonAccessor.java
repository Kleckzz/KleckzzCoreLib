package de.kleckzz.coresystem.bukkit.libraries.plugin.configuration;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

/**
 * @author KeksGauner
 */
public class JsonAccessor {
    private final String fileName;
    private final JavaPlugin plugin;

    private final File configFile;
    private JSONObject fileConfiguration;

    /**
     * Create a instance to access a file
     * @param plugin Require the ProxyPlugin
     * @param fileName Require the name of the file
     */
    public JsonAccessor(JavaPlugin plugin, String fileName) {
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
        try {

            FileReader reader = new FileReader(configFile);
            JSONParser jsonParser = new JSONParser();
            fileConfiguration = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not find config " + configFile, ex);
            ex.printStackTrace();
        }
    }


    /**
     * A way to access the config
     * @return the FileConfiguration
     */
    public JSONObject getConfig() {
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
                Files.write(configFile.toPath(), fileConfiguration.toJSONString().getBytes());
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        } else
            throw new NullPointerException("Could not save config to " + configFile);
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

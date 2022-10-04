# KleckzzCoreLib
Main library file to centralize main functions for multiple plugins which will allow better maintaining capabilities.

# Why is it public?
We will share our work.

# Pre Alpha
This is not a final product!

## Maven
[Look here](https://repo.kleckzz.de/#/snapshots/de/kleckzz/coresystem/lib) for all Versions
```
<!-- CoreSystem -->
<repositories>
    <repository>
        <id>coresystem-waterfall</id>
        <url>https://repo.kleckzz.de/snapshots</url>
    </repository>
</repositories>
    
<dependencies>
    <!-- CoreSystem -->
    <dependency>
        <groupId>de.kleckzz.coresystem.lib</groupId>
        <artifactId>coresystem-waterfall</artifactId>
        <version>1.0-SNAPSHOT</version>
           <scope>compile</scope>
    </dependency>
</dependencies>
```

## Info
Make available into class path, don't add this dependency into final jar if it is normal jar; but add this jar into jar if final jar is a single jar (for example, executable jar)
```
<scope>provided</scope>
```
Dependency will be available at run time environment so don't add this dependency in any case; even not in single jar (i.e. executable jar etc)
```
<scope>compile</scope>
```

# API Examples

### InitializeManager
```
InitializeManager initializeManager;

// Register a Event
initializeManager.registerEvent(Plugin plugin, Listener register)

// Register a Command
initializeManager.registerCommand(Plugin plugin, Command register)
```

## Example Command (Only Bukkit/Spigot server)
This is needed as a dependency: [Link](https://www.spigotmc.org/wiki/spigot-maven/)

```
public class Command extends BukkitCommand {

    public Command(String name) {
        super(name);
        //this.setDescription("Is a KeksGauner Command");
        //this.setPermission("keksgauner.use");
        //this.setUsage("/keksgauner");
        //this.setAliases(Arrays.asList("keksi", "mykeks","superkeks"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }
}
```

### ConfigAccessor
```
// To specify the config name
ConfigAccessor config = new ConfigAccessor(Main.getInstance(), "config.yml");

// To load defaults from the resources into the plugin folder
config.saveDefaultConfig();

config.getConfig().options().copyDefaults(true);

// To save the config
config.saveConfig();

// To reload the config
config.reload();

// To access the configuration
config.getConfig().getString("");
```

### JsonAccessor [not recommend]
This is needed as a dependency: [Link](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)
```
// To specify the config name
JsonAccessor config = new JsonAccessor(Main.getInstance(), "config.json");

// To load defaults from the resources into the plugin folder
config.saveDefaultConfig();

// To reload the config (not recommend)
config.reload();

// To access the configuration
config.getConfig().get("").toString();
```
This is Gson. Did you ever hear about that?
```
Gson g = new Gson();
Student s = g.fromJson(jsonString, Student.class)
String str = g.toJson(p);
```

## Discord
This is needed as a dependency: [Link](https://github.com/DV8FromTheWorld/JDA/releases)
```java
/*
 * Info: This is a Example to send a message with a player head
 */
public class Discord {
    
    private static String token = "TOKEN";

    public static final String pageURL = "https://minotar.net/avatar/";
    // 'https://minotar.net/helm/%7BPlayer%7D/8.png'
    // 'https://cravatar.eu/helmavatar/%7BPlayer%7D/8.png'
    // 'https://mc-heads.net/avatar/%7BPlayer%7D/8'
    
    private static DiscordBot discordBot;

    /**
     * Create a Instance
     */
    public static void createInstance() {
        // Discord bot
        plugin.getLogger().info("Starting Discord Bot");
        discordBot = new DiscordBot(token);

        discordBot.registerEvent(new NotifyDiscord());

        // No commands like cookies :(

        discordBot.start();
    }

    public static DiscordBot getDiscordBot() {
        return discordBot;
    }

    /**
     * MessageEmbed with player-head as author
     * @param player the player
     * @param message the message
     * @return The embed text
     */
    public static MessageEmbed getCustomEmbed(Player player, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.decode("#FF0000"));
        builder.setAuthor(player.getName(), null, pageURL + player.getName());
        builder.setTitle(message);
        builder.setTimestamp(Instant.now());

        return builder.build();
    }

    /**
     * MessageEmbed with player-head as author
     * @param player the player
     * @param color the color
     * @param message the message
     * @return The embed text
     */
    public static MessageEmbed getCustomEmbed(Player player, Color color, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color);
        builder.setAuthor(player.getName(), null, pageURL + player.getName());
        builder.setTitle(message);
        builder.setTimestamp(Instant.now());

        return builder.build();
    }
}
```
### Using EventListener:
```
public class NotifyDiscord extends ListenerAdapter {

    @Override
    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }

}

```
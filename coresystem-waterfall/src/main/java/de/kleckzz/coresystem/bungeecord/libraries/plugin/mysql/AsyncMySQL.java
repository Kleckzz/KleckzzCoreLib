package de.kleckzz.coresystem.bungeecord.libraries.plugin.mysql;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class AsyncMySQL {

    private ExecutorService executorService;
    private Plugin plugin;
    private MySQL mySQL;

    /**
     * Open a mysql connection
     */
    public AsyncMySQL(Plugin plugin, String host, int port, String username, String password, String database) {
        try {
            this.mySQL = new MySQL(host, port, username, password, database);
            this.executorService = Executors.newCachedThreadPool();
            this.plugin = plugin;
            plugin.getLogger().log(Level.INFO, "A database was found. MySQL is now connected.");
        } catch (Exception exception) {
            plugin.getLogger().log(Level.WARNING, "The MySQL data is not correct, enter the data in config");
        }
    }

    public void update(PreparedStatement statement) {
        executorService.execute(() -> mySQL.queryUpdate(statement));
    }

    public void update(String statement) {
        executorService.execute(() -> mySQL.queryUpdate(statement));
    }

    public void query(PreparedStatement statement, Consumer<ResultSet> consumer) {
        executorService.execute(() -> {
            ResultSet result = mySQL.query(statement);
            ProxyServer.getInstance().getScheduler().runAsync(plugin, () -> consumer.accept(result));
        });
    }

    public void query(String statement, Consumer<ResultSet> consumer) {
        executorService.execute(() -> {
            ResultSet result = mySQL.query(statement);
            ProxyServer.getInstance().getScheduler().runAsync(plugin, () -> consumer.accept(result));
        });
    }

    public PreparedStatement prepare(String query) {
        try {
            return mySQL.getConnection().prepareStatement(query);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

}

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
package de.kleckzz.coresystem.bukkit.libraries.plugin.mysql;

import java.sql.*;

/**
 * @author KeksGauner
 */
@SuppressWarnings("unused")
public class MySQL {

    private final String host, username, password, database;
    private final int port;

    public Connection connection;

    public MySQL(String host, int port, String username, String password, String database) throws Exception {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;

        this.openConnection();
    }

    public void queryUpdate(String query) {
        checkConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            queryUpdate(statement);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void queryUpdate(PreparedStatement statement) {
        checkConnection();
        try {
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public ResultSet query(String query) {
        checkConnection();
        try {
            return query(connection.prepareStatement(query));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public ResultSet query(PreparedStatement statement) {
        checkConnection();
        try {
            return statement.executeQuery();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    private void checkConnection() {
        try {
            if (this.connection == null || !this.connection.isValid(10) || this.connection.isClosed()) openConnection();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void openConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            this.connection = null;
        }
    }
}
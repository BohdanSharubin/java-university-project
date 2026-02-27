package org.bohdansharubin.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.nio.file.Paths.*;

/**
 * Class responds of initializing DB before after app is started
 */
public class DatabaseInitializer {

    private final String url;
    private final String user;
    private final String password;
    private final String schemaFilePath;

    public DatabaseInitializer(String url, String user, String password, String schemaFilePath) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.schemaFilePath = schemaFilePath;
    }

    /**
     * Read SQL from file and execute it
     */
    public void init() {
        String sql;
        try {
            sql = Files.readString(get(schemaFilePath));
        } catch (IOException e) {
            System.err.println("Failed to read schema file: " + e.getMessage());
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Schema applied successfully");

        } catch (SQLException e) {
            System.err.println("Error applying schema: " + e.getMessage());
        }
    }
}

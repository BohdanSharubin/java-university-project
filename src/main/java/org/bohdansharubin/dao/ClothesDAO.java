package org.bohdansharubin.dao;

import org.bohdansharubin.models.Clothes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClothesDAO {

    private final String url;
    private final String user;
    private final String password;

    public ClothesDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void insert(Clothes clothes) {
        String sql = "INSERT INTO clothes(type, color, american_size, european_size) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clothes.getType().name());
            statement.setString(2, clothes.getColor());
            statement.setString(3, clothes.getAmericanSize().name());
            statement.setInt(4, clothes.getEuropeanSize());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

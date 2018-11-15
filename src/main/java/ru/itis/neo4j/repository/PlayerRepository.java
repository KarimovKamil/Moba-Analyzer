package ru.itis.neo4j.repository;

import ru.itis.neo4j.models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerRepository {
    private static final String INSERT_PLAYER = "CREATE (pl:Player {" +
            "playerId: {1}, " +
            "playerNickname: {2}, " +
            "playerStatus: {3}, " +
            "playerRating: {4}, " +
            "playerMinRating: {5}, " +
            "playerMaxRating: {6}, " +
            "hoursPlayed: {7}" +
            "});";

    private Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Player player) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_PLAYER);
            statement.setLong(1, player.getPlayerId());
            statement.setString(2, player.getPlayerNickname());
            statement.setString(3, player.getPlayerStatus());
            statement.setInt(4, player.getPlayerRating());
            statement.setInt(5, player.getPlayerMinRating());
            statement.setInt(6, player.getPlayerMaxRating());
            statement.setDouble(7, player.getHoursPlayed());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

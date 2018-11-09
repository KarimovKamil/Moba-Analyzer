package ru.itis.neo4j.repository;

import ru.itis.cassandra.models.Match;
import ru.itis.cassandra.models.Pick;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchRepository {
    private static final String INSERT_MATCH = "CREATE (m:Match {" +
            "matchId: {1}, " +
            "averageRating: {2}, " +
            "startTime: {3}, " +
            "matchDuration: {4}" +
            "});";

    private static final String INSERT_PICK = "MERGE (p:Pick {" +
            "playerId: {1}, " +
            "heroName: {2}, " +
            "netWorth: {3}, " +
            "GPM: {4}, " +
            "XPM: {5}, " +
            "kills: {6}, " +
            "assists: {7}, " +
            "deaths: {8}, " +
            "totalDamage: {9}, " +
            "totalHeal: {10}, " +
            "totalDamageTaken: {11}, " +
            "wardsPlaced: {12}, " +
            "teamGold: {13}" +
            "}) " +
            "MERGE (m {matchId: {14}}) " +
            "MERGE (p)-[r:PLAYED]->(m);";

    private Connection connection;

    public MatchRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Match match) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_MATCH);
            statement.setLong(1, match.getMatchId());
            statement.setInt(2, match.getAverageRating());
            statement.setLong(3, match.getStartTime());
            statement.setLong(4, match.getMatchDuration());
            statement.execute();

            for (Pick pick : match.getPicks()) {
                PreparedStatement pickStatement = connection.prepareStatement(INSERT_PICK);
                pickStatement.setLong(1, pick.getPlayerId());
                pickStatement.setString(2, pick.getHeroName());
                pickStatement.setInt(3, pick.getNetWorth());
                pickStatement.setInt(4, pick.getGPM());
                pickStatement.setInt(5, pick.getXPM());
                pickStatement.setInt(6, pick.getKills());
                pickStatement.setInt(7, pick.getAssists());
                pickStatement.setInt(8, pick.getDeaths());
                pickStatement.setInt(9, pick.getTotalDamage());
                pickStatement.setInt(10, pick.getTotalHeal());
                pickStatement.setInt(11, pick.getTotalDamageTaken());
                pickStatement.setInt(12, pick.getWardsPlaced());
                pickStatement.setInt(13, pick.getTeamGold());
                pickStatement.setLong(14, match.getMatchId());
                pickStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

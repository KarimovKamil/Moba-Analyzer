package ru.itis.neo4j.repository;


import ru.itis.neo4j.models.Match;
import ru.itis.neo4j.models.Pick;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchRepository {
    private static final String INSERT_MATCH = "CREATE (m:Match {" +
            "matchId: {1}, " +
            "isDireWin: {2}, " +
            "averageRating: {3}, " +
            "startTime: {4}, " +
            "matchDuration: {5}" +
            "});";

    private static final String INSERT_PICK = "MERGE (p:Pick {" +
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
            "teamGold: {13}, " +
            "isDire: {14}" +
            "}) " +
            "MERGE (m {matchId: {15}}) " +
            "MERGE (h {heroName: {2}}) " +
            "MERGE (pl {playerId: {1}}) " +
            "MERGE (p)-[rm:PLAYED]->(m) " +
            "MERGE (p)-[rh:PICKED]->(h) " +
            "MERGE (p)-[rpl:LINK]->(pl);";

    private Connection connection;

    public MatchRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Match match) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_MATCH);
            statement.setLong(1, match.getMatchId());
            statement.setBoolean(2, match.isDireWin());
            statement.setInt(3, match.getAverageRating());
            statement.setLong(4, match.getStartTime());
            statement.setLong(5, match.getMatchDuration());
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
                pickStatement.setBoolean(14, pick.isDire());
                pickStatement.setLong(15, match.getMatchId());
                pickStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ru.itis.cassandra.repository.table;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import ru.itis.cassandra.models.Player;

public class PlayerRepository {
    private static final String TABLE_NAME = "player";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME +
            " (player_id, player_nickname, player_status, player_rating, " +
            "player_min_rating, player_max_rating, hours_played) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private Session session;

    public PlayerRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("player_id bigint, ")
                .append("player_nickname text, ")
                .append("player_status text, ")
                .append("player_rating int, ")
                .append("player_min_rating int, ")
                .append("player_max_rating int, ")
                .append("hours_played double, ")
                .append("PRIMARY KEY(").append("player_id").append(")")
                .append(");");

        session.execute(sb.toString());
    }

    public void dropTable() {
        StringBuilder sb = new StringBuilder()
                .append("DROP TABLE IF EXISTS ")
                .append(TABLE_NAME).append(";");

        session.execute(sb.toString());
    }

    public void insert(Player player) {
        PreparedStatement preparedStatement = session.prepare(INSERT);

        session.execute(preparedStatement.bind(
                player.getPlayerId(),
                player.getPlayerNickname(),
                player.getPlayerStatus(),
                player.getPlayerRating(),
                player.getPlayerMinRating(),
                player.getPlayerMaxRating(),
                player.getHoursPlayed()));
    }
}

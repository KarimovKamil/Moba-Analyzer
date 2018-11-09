package ru.itis.cassandra.repository.table;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class MatchViewsRepository {
    private static final String TABLE_NAME = "match_views_count";
    private static final String UPDATE = "UPDATE " + TABLE_NAME +
            " SET match_view_count = match_view_count + 1 WHERE match_id = ?;";

    private Session session;

    public MatchViewsRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("match_id bigint, ")
                .append("match_view_count counter, ")
                .append("PRIMARY KEY(").append("match_id").append(")")
                .append(");");

        session.execute(sb.toString());
    }

    public void dropTable() {
        StringBuilder sb = new StringBuilder()
                .append("DROP TABLE IF EXISTS ")
                .append(TABLE_NAME).append(";");

        session.execute(sb.toString());
    }

    public void update(long matchId) {
        PreparedStatement preparedStatement = session.prepare(UPDATE);

        session.execute(preparedStatement.bind(matchId));
    }
}

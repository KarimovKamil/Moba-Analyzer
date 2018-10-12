package ru.itis.repository;

import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itis.models.Match;

public class MatchRepository {
    private static final String TABLE_NAME = "match";

    private Session session;
    private Gson gson;

    public MatchRepository(Session session) {
        this.session = session;
        this.gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("match_id bigint, ")
                .append("data text, ")
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

    public void insert(Match match) {
        String json = gson.toJson(match);
        json = json.substring(json.indexOf(',') + 1);
        json = '{' + json;

        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO ")
                .append(TABLE_NAME).append(" (match_id, data) ")
                .append("VALUES (").append(match.getMatchId()).append(", '")
                .append(json).append("');");

        session.execute(sb.toString());
    }

    public Match getMatchById(long matchId) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT * FROM ").append(TABLE_NAME)
                .append(" WHERE match_id = ").append(matchId).append(";");

        String row = session.execute(sb.toString()).one().toString();
        row = row.substring(row.indexOf(',') + 3, row.lastIndexOf(']'));
        row = "{\"matchId\":" + matchId + ", " + row;

        Match match = gson.fromJson(row, Match.class);
        return match;
    }
}

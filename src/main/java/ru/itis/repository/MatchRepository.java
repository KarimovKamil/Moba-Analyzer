package ru.itis.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itis.models.Match;

import java.util.ArrayList;
import java.util.List;

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
                .append(TABLE_NAME)
                .append(" (match_id, data) ")
                .append("VALUES (").append(match.getMatchId()).append(", '")
                .append(json).append("');");

        session.execute(sb.toString());
    }

    public Match getMatchById(long matchId) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT * FROM ").append(TABLE_NAME)
                .append(" WHERE match_id = ").append(matchId).append(";");

        Row row = session.execute(sb.toString()).one();
        StringBuilder json = new StringBuilder()
                .append("{\"matchId\":")
                .append(row.getLong("match_id"))
                .append(", ")
                .append(row.getString("data").substring(1));

        Match match = gson.fromJson(json.toString(), Match.class);
        return match;
    }

    public List<Match> getAllMatches() {
        StringBuilder sb = new StringBuilder()
                .append("SELECT * FROM ").append(TABLE_NAME).append(";");

        List<Match> matches = new ArrayList<>();

        ResultSet rs = session.execute(sb.toString());
        for (Row row : rs) {
            StringBuilder json = new StringBuilder()
                    .append("{\"matchId\":")
                    .append(row.getLong("match_id"))
                    .append(", ")
                    .append(row.getString("data").substring(1));

            Match match = gson.fromJson(json.toString(), Match.class);
            matches.add(match);
        }

        return matches;
    }
}

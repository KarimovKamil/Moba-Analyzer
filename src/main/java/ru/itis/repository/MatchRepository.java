package ru.itis.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import ru.itis.models.Match;
import ru.itis.models.Pick;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository {
    private static final String TABLE_NAME = "match";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME +
            " (match_id, average_rating, start_time, match_duration, player_id, hero_name, net_worth, gpm, xpm, " +
            "kills, assists, deaths, total_damage, total_heal, total_damage_taken, wards_placed, team_gold) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME +
            " WHERE match_id = ?;";

    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME + ";";

    private Session session;
    private MatchViewsRepository matchViewsRepository;

    public MatchRepository(Session session) {
        this.session = session;
        this.matchViewsRepository = new MatchViewsRepository(session);
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("match_id bigint, ")
                .append("average_rating int, ")
                .append("start_time bigint, ")
                .append("match_duration bigint, ")
                .append("player_id bigint, ")
                .append("hero_name text, ")
                .append("net_worth int, ")
                .append("gpm int, ")
                .append("xpm int, ")
                .append("kills int, ")
                .append("assists int, ")
                .append("deaths int, ")
                .append("total_damage int, ")
                .append("total_heal int, ")
                .append("total_damage_taken int, ")
                .append("wards_placed int, ")
                .append("team_gold int, ")
                .append("PRIMARY KEY(").append("match_id, player_id, hero_name").append(")")
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
        PreparedStatement preparedStatement = session.prepare(INSERT);

        for (Pick pick : match.getPicks()) {
            session.execute(preparedStatement.bind(
                    match.getMatchId(),
                    match.getAverageRating(),
                    match.getStartTime(),
                    match.getMatchDuration(),
                    pick.getPlayerId(),
                    pick.getHeroName(),
                    pick.getNetWorth(),
                    pick.getGPM(),
                    pick.getXPM(),
                    pick.getKills(),
                    pick.getAssists(),
                    pick.getDeaths(),
                    pick.getTotalDamage(),
                    pick.getTotalHeal(),
                    pick.getTotalDamageTaken(),
                    pick.getWardsPlaced(),
                    pick.getTeamGold()));
        }
    }

    public Match getMatchById(long matchId) {
        matchViewsRepository.update(matchId);

        PreparedStatement preparedStatement = session.prepare(SELECT_BY_ID);

        ResultSet resultSet = session.execute(preparedStatement.bind(matchId));

        List<Pick> picks = new ArrayList<>();

        boolean first = true;

        Match.MatchBuilder builder = Match.builder();

        for (Row row : resultSet) {
            if (first) {
                builder
                        .matchId(row.getLong("match_id"))
                        .averageRating(row.getInt("average_rating"))
                        .startTime(row.getLong("start_time"))
                        .matchDuration(row.getLong("match_duration"));
                first = false;
            }
            Pick pick = Pick.builder()
                    .playerId(row.getLong("player_id"))
                    .heroName(row.getString("hero_name"))
                    .netWorth(row.getInt("net_worth"))
                    .GPM(row.getInt("gpm"))
                    .XPM(row.getInt("xpm"))
                    .kills(row.getInt("kills"))
                    .assists(row.getInt("assists"))
                    .deaths(row.getInt("deaths"))
                    .totalDamage(row.getInt("total_damage"))
                    .totalHeal(row.getInt("total_heal"))
                    .totalDamageTaken(row.getInt("total_damage_taken"))
                    .wardsPlaced(row.getInt("wards_placed"))
                    .teamGold(row.getInt("team_gold"))
                    .build();

            picks.add(pick);
        }

        Match match = builder
                .picks(picks)
                .build();

        return match;
    }

    public List<Match> getAllMatches() {
        ResultSet resultSet = session.execute(SELECT_ALL);

        List<Match> matches = new ArrayList<>();

        int count = 0;
        List<Pick> picks = new ArrayList<>();
        for (Row row : resultSet) {
            count++;
//            System.out.println(row.getLong("match_id") + " " + count);
            Pick pick = Pick.builder()
                    .playerId(row.getLong("player_id"))
                    .heroName(row.getString("hero_name"))
                    .netWorth(row.getInt("net_worth"))
                    .GPM(row.getInt("gpm"))
                    .XPM(row.getInt("xpm"))
                    .kills(row.getInt("kills"))
                    .assists(row.getInt("assists"))
                    .deaths(row.getInt("deaths"))
                    .totalDamage(row.getInt("total_damage"))
                    .totalHeal(row.getInt("total_heal"))
                    .totalDamageTaken(row.getInt("total_damage_taken"))
                    .wardsPlaced(row.getInt("wards_placed"))
                    .teamGold(row.getInt("team_gold"))
                    .build();

            picks.add(pick);

            if (count == 10) {
                Match match = Match.builder()
                        .matchId(row.getLong("match_id"))
                        .averageRating(row.getInt("average_rating"))
                        .startTime(row.getLong("start_time"))
                        .matchDuration(row.getLong("match_duration"))
                        .picks(picks)
                        .build();

                matches.add(match);

                picks = new ArrayList<>();
                count = 0;
            }
        }

        return matches;
    }
}

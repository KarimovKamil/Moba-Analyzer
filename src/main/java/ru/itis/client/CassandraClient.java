package ru.itis.client;

import com.datastax.driver.core.Session;
import ru.itis.connector.CassandraConnector;
import ru.itis.models.Match;
import ru.itis.models.Pick;
import ru.itis.repository.KeyspaceRepository;
import ru.itis.repository.MatchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CassandraClient {
    public static void main(String[] args) {
        Random random = new Random();
        CassandraConnector connector = new CassandraConnector("127.0.0.1", null);

        Session session = connector.getSession();

        KeyspaceRepository keyspaceRepository = new KeyspaceRepository(session);
        keyspaceRepository.useKeyspace("testks");

        MatchRepository matchRepository = new MatchRepository(session);

        matchRepository.dropTable();

        matchRepository.createTable();

        for (int i = 1; i <= 10; i++) {
            List<Pick> picks = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Pick pick = Pick.builder()
                        .playerId(random.nextInt(Integer.MAX_VALUE) + 1)
                        .heroName(UUID.randomUUID().toString())
                        .netWorth(random.nextInt(30000) + 3000)
                        .GPM(random.nextInt(1000) + 150)
                        .XPM(random.nextInt(1000) + 150)
                        .kills(random.nextInt(60))
                        .assists(random.nextInt(60))
                        .deaths(random.nextInt(60))
                        .totalDamageTaken(random.nextInt(100000))
                        .totalHeal(random.nextInt(50000))
                        .totalDamageTaken(100000)
                        .wardsPlaced(random.nextInt(100))
                        .teamGold(random.nextInt(10000))
                        .build();

                picks.add(pick);
            }
            Match match = Match.builder()
                    .matchId(i)
                    .averageRating(random.nextInt(6000) + 1000)
                    .startTime(random.nextInt(Integer.MAX_VALUE))
                    .matchDuration(random.nextInt(Integer.MAX_VALUE))
                    .picks(picks)
                    .build();

            matchRepository.insert(match);
        }

        Match match = matchRepository.getMatchById(2);
        System.out.println(match.getAverageRating());

        System.out.println(matchRepository.getMatchById(10).getMatchDuration());

        connector.close();
    }
}

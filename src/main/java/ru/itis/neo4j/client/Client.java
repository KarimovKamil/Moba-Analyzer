package ru.itis.neo4j.client;

import ru.itis.cassandra.models.Match;
import ru.itis.cassandra.models.Pick;
import ru.itis.neo4j.connector.Connector;
import ru.itis.neo4j.repository.MatchRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {
        Random random = new Random();

        Connector connector = new Connector("neo4j", "1428");
        Connection connection = connector.getConnection();

        MatchRepository matchRepository = new MatchRepository(connection);

        int matchesCount = 3;
        for (int i = 1; i <= matchesCount; i++) {
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
                        .totalDamage(random.nextInt(100000))
                        .totalHeal(random.nextInt(50000))
                        .totalDamageTaken(random.nextInt(100000))
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
    }
}

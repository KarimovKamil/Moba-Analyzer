package ru.itis.client;

import com.datastax.driver.core.Session;
import ru.itis.connector.CassandraConnector;
import ru.itis.models.*;
import ru.itis.repository.*;

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

        MatchViewsRepository matchViewsRepository = new MatchViewsRepository(session);
        matchViewsRepository.dropTable();
        matchViewsRepository.createTable();

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

            matchViewsRepository.update(match.getMatchId());
        }

        Match match = matchRepository.getMatchById(2);
        System.out.println(match.getAverageRating());

        List<Match> matches = matchRepository.getAllMatches();
        System.out.println(matches.size());

        HeroRepository heroRepository = new HeroRepository(session);
        heroRepository.dropTable();
        heroRepository.createTable();

        for (int i = 1; i <= 10; i++) {
            Hero hero = Hero.builder()
                    .heroName(UUID.randomUUID().toString())
                    .strength(random.nextInt(100) + 10)
                    .agility(random.nextInt(100) + 10)
                    .intelligence(random.nextInt(100) + 10)
                    .minPower(random.nextInt(50) + 60)
                    .maxPower(random.nextInt(50) + 200)
                    .attackSpeed(random.nextInt(100) + 100)
                    .attackRange(random.nextInt(400) + 500)
                    .movementSpeed(random.nextInt(200) + 300)
                    .health(random.nextInt(1000) + 800)
                    .mana(random.nextInt(1000) + 800)
                    .healthRegen(random.nextInt(40))
                    .manaRegen(random.nextInt(40))
                    .armor(random.nextInt(70) + 20)
                    .build();
            heroRepository.insert(hero);
        }

        PlayerRepository playerRepository = new PlayerRepository(session);

        playerRepository.dropTable();
        playerRepository.createTable();

        for (int i = 1; i <= 10; i++) {
            Player player = Player.builder()
                    .playerId(i)
                    .playerNickname(UUID.randomUUID().toString())
                    .playerStatus(UUID.randomUUID().toString())
                    .playerRating(random.nextInt(6000) + 1000)
                    .playerMinRating(random.nextInt(3000))
                    .playerMaxRating(random.nextInt(6000) + 1500)
                    .hoursPlayed(random.nextInt(30000))
                    .build();
            playerRepository.insert(player);
        }

        SpellRepository spellRepository = new SpellRepository(session);

        spellRepository.dropTable();
        spellRepository.createTable();

        for (int i = 1; i <= 10; i++) {
            Spell spell = Spell.builder()
                    .spellName(UUID.randomUUID().toString())
                    .description(UUID.randomUUID().toString())
                    .heroName(UUID.randomUUID().toString())
                    .build();
            spellRepository.insert(spell);
        }

        connector.close();
    }
}

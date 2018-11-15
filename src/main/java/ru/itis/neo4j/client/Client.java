package ru.itis.neo4j.client;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.neo4j.connector.Connector;
import ru.itis.neo4j.dto.HeroDto;
import ru.itis.neo4j.models.Match;
import ru.itis.neo4j.models.Pick;
import ru.itis.neo4j.models.Player;
import ru.itis.neo4j.repository.HeroRepository;
import ru.itis.neo4j.repository.MatchRepository;
import ru.itis.neo4j.repository.PlayerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Client {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new Gson();

        Connector connector = new Connector("neo4j", "1428");
        Connection connection = connector.getConnection();

        try {
            connection.createStatement().execute("MATCH (n)-[r]-() DETACH DELETE n, r;");
            connection.createStatement().execute("MATCH (n) DETACH DELETE n;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate
                .getForEntity("https://api.opendota.com/api/heroStats", String.class);
        String json = response.getBody();
        HeroDto[] heroArray = gson.fromJson(json, HeroDto[].class);

        HeroRepository heroRepository = new HeroRepository(connection);
        for (HeroDto heroDto : heroArray) {
            heroRepository.insert(heroDto);
        }

        PlayerRepository playerRepository = new PlayerRepository(connection);
        String[] nicknames = {"Halva", "KHR3b", "flood", "Moseszzzz", "Preysme", "domo1", "Preysme", "Manymuch",
                "Yarilo", "Klevanso", "Molochko", "Tatarin", "Hahabuka", "Alexander", "Nijniy_slesh", "Ya_Ajdar",
                "Myr4alka", "qDog", "Solo", "XBOCT", "Ramzes666", "Lil", "Dendi", "S4", "EvilArthas", "ubicanoobov",
                "Ninja", "CheatBanned", "IllidanSTR", "AlohaDance"};
        Player[] players = new Player[nicknames.length];
        for (int i = 1; i <= nicknames.length; i++) {
            Player player = Player.builder()
                    .playerId(i)
                    .playerNickname(nicknames[i - 1])
                    .playerStatus(UUID.randomUUID().toString())
                    .playerRating(RANDOM.nextInt(5000) + 2000)
                    .playerMinRating(RANDOM.nextInt(5000) + 2000)
                    .playerMaxRating(RANDOM.nextInt(5000) + 2000)
                    .hoursPlayed(RANDOM.nextInt(8000) + 2000)
                    .build();
            players[i - 1] = player;
            playerRepository.insert(player);
        }

        Boolean[] booleans = {true, true, true, true, true, false, false, false, false, false};
        MatchRepository matchRepository = new MatchRepository(connection);
        int matchesCount = 1;
        for (int i = 1; i <= matchesCount; i++) {
            shuffle(players);
            shuffle(heroArray);
            shuffle(booleans);
            List<Pick> picks = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Pick pick = Pick.builder()
                        .playerId(players[j].getPlayerId())
                        .heroName(heroArray[j].getLocalized_name())
                        .isDire(booleans[j])
                        .netWorth(RANDOM.nextInt(20000) + 5000)
                        .GPM(RANDOM.nextInt(750) + 250)
                        .XPM(RANDOM.nextInt(750) + 250)
                        .kills(RANDOM.nextInt(60))
                        .assists(RANDOM.nextInt(60))
                        .deaths(RANDOM.nextInt(60))
                        .totalDamage(RANDOM.nextInt(100000))
                        .totalHeal(RANDOM.nextInt(50000))
                        .totalDamageTaken(RANDOM.nextInt(100000))
                        .wardsPlaced(RANDOM.nextInt(100))
                        .teamGold(RANDOM.nextInt(10000))
                        .build();
                picks.add(pick);
            }
            Match match = Match.builder()
                    .matchId(i)
                    .isDireWin(booleans[0])
                    .averageRating(RANDOM.nextInt(6000) + 1000)
                    .startTime(RANDOM.nextInt(Integer.MAX_VALUE))
                    .matchDuration(RANDOM.nextInt(Integer.MAX_VALUE))
                    .picks(picks)
                    .build();
            matchRepository.insert(match);
        }
    }

    private static void shuffle(Object[] array) {
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i);
            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

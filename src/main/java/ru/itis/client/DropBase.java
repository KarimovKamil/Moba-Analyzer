package ru.itis.client;

import com.datastax.driver.core.Session;
import ru.itis.connector.CassandraConnector;
import ru.itis.repository.*;
import ru.itis.repository.keyspace.KeyspaceRepository;

public class DropBase {
    public static void main(String[] args) {
        CassandraConnector connector = new CassandraConnector("127.0.0.1", null);
        Session session = connector.getSession();

        String keyspace = "testks";

        KeyspaceRepository keyspaceRepository = new KeyspaceRepository(session);
        keyspaceRepository.useKeyspace(keyspace);

        MatchRepository matchRepository = new MatchRepository(session);
        HeroRepository heroRepository = new HeroRepository(session);
        MatchViewsRepository matchViewsRepository = new MatchViewsRepository(session);
        PlayerRepository playerRepository = new PlayerRepository(session);
        SpellRepository spellRepository = new SpellRepository(session);

        matchRepository.dropTable();
        heroRepository.dropTable();
        matchViewsRepository.dropTable();
        playerRepository.dropTable();
        spellRepository.dropTable();

        connector.close();
    }
}

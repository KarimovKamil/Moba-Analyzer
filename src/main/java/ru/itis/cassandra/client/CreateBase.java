package ru.itis.cassandra.client;

import com.datastax.driver.core.Session;
import ru.itis.cassandra.connector.CassandraConnector;
import ru.itis.cassandra.repository.keyspace.KeyspaceRepository;
import ru.itis.cassandra.repository.table.*;

public class CreateBase {
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

        matchRepository.createTable();
        heroRepository.createTable();
        matchViewsRepository.createTable();
        playerRepository.createTable();
        spellRepository.createTable();

        connector.close();
    }
}

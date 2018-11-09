package ru.itis.cassandra.repository.keyspace;

import com.datastax.driver.core.Session;

public class KeyspaceRepository {
    private Session session;

    public KeyspaceRepository(Session session) {
        this.session = session;
    }

    public void useKeyspace(String keyspace) {
        session.execute("USE " + keyspace + ";");
    }
}

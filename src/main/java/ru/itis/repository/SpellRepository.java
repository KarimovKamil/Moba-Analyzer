package ru.itis.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import ru.itis.models.Spell;

public class SpellRepository {
    private static final String TABLE_NAME = "spell";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (spell_name, " +
            "description, hero_name) VALUES (?, ?, ?);";

    private Session session;

    public SpellRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("spell_name text, ")
                .append("description text, ")
                .append("hero_name text, ")
                .append("PRIMARY KEY(").append("spell_name").append(")")
                .append(");");

        session.execute(sb.toString());
    }

    public void dropTable() {
        StringBuilder sb = new StringBuilder()
                .append("DROP TABLE IF EXISTS ")
                .append(TABLE_NAME).append(";");

        session.execute(sb.toString());
    }

    public void insert(Spell spell) {
        PreparedStatement preparedStatement = session.prepare(INSERT);

        session.execute(preparedStatement.bind(spell.getSpellName(),
                spell.getDescription(),
                spell.getHeroName()));
    }
}

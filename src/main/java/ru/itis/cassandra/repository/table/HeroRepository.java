package ru.itis.cassandra.repository.table;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import ru.itis.cassandra.models.Hero;

public class HeroRepository {
    private static final String TABLE_NAME = "hero";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME +
            " (hero_name, strength, agility, intelligence, min_power, max_power, attack_speed, attack_range, " +
            "movement_speed, health, mana, health_regen, mana_regen, armor) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private Session session;

    public HeroRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("hero_name text, ")
                .append("strength int, ")
                .append("agility int, ")
                .append("intelligence int, ")
                .append("min_power int, ")
                .append("max_power int, ")
                .append("attack_speed int, ")
                .append("attack_range int, ")
                .append("movement_speed int, ")
                .append("health int, ")
                .append("mana int, ")
                .append("health_regen double, ")
                .append("mana_regen double, ")
                .append("armor double, ")
                .append("PRIMARY KEY(").append("hero_name").append(")")
                .append(");");

        session.execute(sb.toString());
    }

    public void dropTable() {
        StringBuilder sb = new StringBuilder()
                .append("DROP TABLE IF EXISTS ")
                .append(TABLE_NAME).append(";");

        session.execute(sb.toString());
    }

    public void insert(Hero hero) {
        PreparedStatement preparedStatement = session.prepare(INSERT);

        session.execute(preparedStatement.bind(
                hero.getHeroName(),
                hero.getStrength(),
                hero.getAgility(),
                hero.getIntelligence(),
                hero.getMinPower(),
                hero.getMaxPower(),
                hero.getAttackSpeed(),
                hero.getAttackRange(),
                hero.getMovementSpeed(),
                hero.getHealth(),
                hero.getMana(),
                hero.getHealthRegen(),
                hero.getManaRegen(),
                hero.getArmor()));
    }
}

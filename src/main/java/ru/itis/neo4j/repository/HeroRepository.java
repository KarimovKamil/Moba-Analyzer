package ru.itis.neo4j.repository;

import ru.itis.neo4j.dto.HeroDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HeroRepository {
    private static final String INSERT_HERO = "CREATE (h:Hero {" +
            "heroName: {1}, " +
            "strength: {2}, " +
            "agility: {3}, " +
            "intelligence: {4}, " +
            "minPower: {5}, " +
            "maxPower: {6}, " +
            "attackSpeed: {7}, " +
            "attackRange: {8}, " +
            "movementSpeed: {9}, " +
            "health: {10}, " +
            "mana: {11}, " +
            "healthRegen: {12}, " +
            "manaRegen: {13}, " +
            "armor: {14}" +
            "});";

    private Connection connection;

    public HeroRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(HeroDto heroDto) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_HERO);
            statement.setString(1, heroDto.getLocalized_name());
            statement.setInt(2, heroDto.getBase_str());
            statement.setInt(3, heroDto.getBase_agi());
            statement.setInt(4, heroDto.getBase_int());
            statement.setInt(5, heroDto.getBase_attack_min());
            statement.setInt(6, heroDto.getBase_attack_max());
            statement.setDouble(7, heroDto.getAttack_rate());
            statement.setInt(8, heroDto.getAttack_range());
            statement.setInt(9, heroDto.getMove_speed());
            statement.setInt(10, heroDto.getBase_health());
            statement.setInt(11, heroDto.getBase_mana());
            statement.setDouble(12, heroDto.getBase_health_regen());
            statement.setDouble(13, heroDto.getBase_mana_regen());
            statement.setDouble(14, heroDto.getBase_armor());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

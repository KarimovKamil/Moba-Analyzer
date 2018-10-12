package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero {
    private String heroName;
    private int strength;
    private int agility;
    private int intelligence;
    private int minPower;
    private int maxPower;
    private int attackSpeed;
    private int attackRange;
    private int movementSpeed;
    private int health;
    private int mana;
    private double healthRegen;
    private double manaRegen;
    private double armor;
}

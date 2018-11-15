package ru.itis.neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroDto {
    private String localized_name;
    private int base_str;
    private int base_agi;
    private int base_int;
    private int base_attack_min;
    private int base_attack_max;
    private double attack_rate;
    private int attack_range;
    private int move_speed;
    private int base_health;
    private int base_mana;
    private double base_health_regen;
    private double base_mana_regen;
    private double base_armor;
}

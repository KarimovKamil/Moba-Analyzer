package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pick {
    private long playerId;
    private String heroName;
    private int netWorth;
    private int GPM;
    private int XPM;
    private int kills;
    private int assists;
    private int deaths;
    private int totalDamage;
    private int totalHeal;
    private int totalDamageTaken;
    private int wardsPlaced;
    private int teamGold;
}

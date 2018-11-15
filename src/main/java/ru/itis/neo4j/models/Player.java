package ru.itis.neo4j.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private long playerId;
    private String playerNickname;
    private String playerStatus;
    private int playerRating;
    private int playerMinRating;
    private int playerMaxRating;
    private double hoursPlayed;
}
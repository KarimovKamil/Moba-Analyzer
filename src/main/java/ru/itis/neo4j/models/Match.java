package ru.itis.neo4j.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    private long matchId;
    private boolean isDireWin;
    private int averageRating;
    private long startTime;
    private long matchDuration;
    private List<Pick> picks;
}


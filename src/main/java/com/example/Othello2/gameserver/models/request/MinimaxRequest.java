package com.example.Othello2.gameserver.models.request;

import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.Cell;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class MinimaxRequest {
    private Cell[][] cells;
    private int depth;
    private double evaluationValue;
    private Player currentPlayer;
}

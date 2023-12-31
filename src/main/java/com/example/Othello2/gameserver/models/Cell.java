package com.example.Othello2.gameserver.models;

import com.example.Othello2.gameserver.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Cell {
    private int x;
    private int y;
    private Player piece;

    public static class CellListConvertToArray{
        public static Cell[][] convert(List<Cell> cells, Integer size){
            Cell[][] result = new Cell[size][size];

            int sequence = 0;

            for (int i = 0 ; i < size; i++){
                for (int j = 0 ; j < size; j++){
                    result[i][j] = cells.get(sequence);
                    sequence++;
                }
            }

            return result;
        }
    }

}








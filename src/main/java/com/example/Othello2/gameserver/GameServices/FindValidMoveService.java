package com.example.Othello2.gameserver.GameServices;

import com.example.Othello2.gameserver.GameServices.helpers.IsValidMovesService;
import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.Cell;
import com.example.Othello2.gameserver.models.Move;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FindValidMoveService {

    private final IsValidMovesService isValidMovesService;

    public List<Move> findValidMoves(Cell[][] cells, Player player) {

        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Move move = isValidMovesService.isValidMoves(cells, i, j, player);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }
}

package com.example.Othello2.gameserver.GameServices.helpers;

import com.example.Othello2.gameserver.enums.Direction;
import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.Cell;
import com.example.Othello2.gameserver.models.Move;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class IsValidMovesService {

    private final IsInsideBoardService isInsideBoardService;

    public Move isValidMoves(Cell[][] cells, int row, int col, Player player) {

        // nếu ô đã có cờ
        if (!cells[row][col].getPiece().equals(Player.EMPTY)) {
            return null;
        }
        //Những ô cờ bắt đầu
        List<Cell> froms = new ArrayList<>();
        // những ô cờ bị lật
        List<Cell> flips = new ArrayList<>();

        // duyệt qua các ô lân cận
        for (Direction direction : Direction.values()) {
            int adjacentX = row + direction.getX();
            int adjacentY = col + direction.getY();

            // nếu tọa độ x, y nằm trong bàn cờ
            if (Boolean.TRUE.equals(isInsideBoardService.check(adjacentX, adjacentY))) {

                Cell adjacentCell = cells[adjacentX][adjacentY];
                List<Cell> _flipCells = new ArrayList<>();
                // nếu ô cờ là của đối thủ
                if (Objects.nonNull(adjacentCell.getPiece()) &&
                        !Player.EMPTY.equals(adjacentCell.getPiece()) &&
                        !player.equals(adjacentCell.getPiece())){

                    _flipCells.add(adjacentCell);
                    int x = adjacentX + direction.getX();
                    int y = adjacentY + direction.getY();

                    // Kiểm tra trên đường thẳng/ chéo nối từ ô hiện tại tới ô có quân cờ của người chơi hiện tại
                    while (Boolean.TRUE.equals(isInsideBoardService.check(x, y))) {
                        Cell currentCell = cells[x][y];
                        // Nếu ô hiện tại không có quân cờ
                        if (currentCell.getPiece().equals(Player.EMPTY)) {
                            break;
                        }
                        // Nếu ô hiện tại có quân cờ của người chơi hiện tại
                        if (currentCell.getPiece().equals(player)) {
                            froms.add(currentCell);
                            flips.addAll(_flipCells);
                            break; // Đã tìm thấy quân cờ của người hiện tại, nước đi hợp lệ
                        }
                        // Nếu ô hiện tại có quân cờ của đối thủ
                        _flipCells.add(currentCell);
                        x += direction.getX();
                        y += direction.getY();
                    }
                }
            }
        }
        if(!froms.isEmpty()){
            return new Move(List.of(froms.toArray(new Cell[0])), cells[row][col], List.of(flips.toArray(new Cell[0])));
        }
        else{
            return null;
        }
    }
}

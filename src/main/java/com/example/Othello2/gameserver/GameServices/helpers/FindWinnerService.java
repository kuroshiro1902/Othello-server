package com.example.Othello2.gameserver.GameServices.helpers;

import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.PlayerStats;
import com.example.Othello2.gameserver.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class FindWinnerService {
    private Utils utils;
    public Player findWinner(PlayerStats currentPlayerStats, PlayerStats opponentPlayerStats){
        //Nếu bàn cờ full quân cờ
        if(currentPlayerStats.getPieces().size() + opponentPlayerStats.getPieces().size() == 64){
            return this.getWinnerByAmount(currentPlayerStats.getPlayer(), currentPlayerStats.getPieces().size(), opponentPlayerStats.getPlayer(), opponentPlayerStats.getPieces().size());
        }
        //Nếu cả 2 không còn nước đi hợp lệ
        if(currentPlayerStats.getValidMovesAmount() == 0 && opponentPlayerStats.getValidMovesAmount()==0) {
            return this.getWinnerByAmount(currentPlayerStats.getPlayer(), currentPlayerStats.getPieces().size(), opponentPlayerStats.getPlayer(), opponentPlayerStats.getPieces().size());
        }

        return Player.EMPTY;
    }

    public Player getWinnerByAmount(Player currentPlayer, int currentPlayerPieceAmount, Player opponentPlayer, int opponentPlayerPieceAmount){
        //Người chơi hiện tại thắng
        if(currentPlayerPieceAmount > opponentPlayerPieceAmount){
            return currentPlayer;
        }
        //Đối thủ thắng
        else if(opponentPlayerPieceAmount > currentPlayerPieceAmount){
            return opponentPlayer;
        }
        //Hòa
        else return Player.BOTH;
    }
}

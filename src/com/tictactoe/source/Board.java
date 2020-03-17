package com.tictactoe.source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    public enum gameBoard{
        X, O, BLANK;
    }


    private gameBoard[][] board = new gameBoard[][]{
            {gameBoard.BLANK, gameBoard.BLANK, gameBoard.BLANK},
            {gameBoard.BLANK, gameBoard.BLANK, gameBoard.BLANK},
            {gameBoard.BLANK, gameBoard.BLANK, gameBoard.BLANK}
    };


    public gameBoard[][] getgameBoard(){
        return board;
    }


    public void place(Integer[] position, gameBoard player){
        board[position[0]][position[1]] = player;
    }


    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            System.out.print(2 - i + " ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != gameBoard.BLANK) {
                    System.out.print(" " + board[i][j] + (j + 1 == board[i].length ? " " : " |"));
                } else {
                    System.out.print("  " + (j + 1 == board[i].length ? " " : " |"));
                }
            }
            System.out.print(i + 1 == board.length ? "" : "\n  -----------\n");
        }

        System.out.println("\n   0   1   2");
        System.out.println();
    }


    public List<Integer[]> getAvailablePositions() {
        ArrayList<Integer[]> availablePositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Board.gameBoard.BLANK) {
                    availablePositions.add(new Integer[]{i, j});
                }
            }
        }
        return availablePositions;
    }


    public gameBoard checkWin(){
        gameBoard check_players;
        //check columns for 3 in a row
        for (int i = 0; i < board.length; i++) {
            check_players = board[0][i];
            if (check_players == gameBoard.BLANK) {
                continue;
            }
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != check_players) {
                    check_players = null;
                    break;
                }
            }
            if (check_players != null) {
                return check_players;
            }
        }

        //check rows for 3 in a row
        for (gameBoard[] cellStates : board) {
            check_players = cellStates[0];
            if (check_players == gameBoard.BLANK) {
                continue;
            }
            for (gameBoard cellState : cellStates) {
                if (cellState != check_players) {
                    check_players = null;
                    break;
                }
            }
            if (check_players != null) {
                return check_players;
            }
        }

        //check diagonals for 3 in a row
        check_players = board[0][0];
        if (board[1][1] == check_players && board[2][2] == check_players && check_players != gameBoard.BLANK) {
            return check_players;
        }

        check_players = board[2][0];
        if (board[1][1] == check_players && board[0][2] == check_players && check_players != gameBoard.BLANK) {
            return check_players;
        }

        //check in case of a tie
        for (gameBoard[] cellStates : board) {
            for (gameBoard cellState : cellStates) {
                if (cellState == gameBoard.BLANK) {
                    return null;
                }
            }
        }

        return gameBoard.BLANK;
    }

}

package com.tictactoe.source;
import java.util.Scanner;

import static com.tictactoe.source.Artificial_Intelligence.*;

public class Main {

    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        Board board = new Board();
        board.printBoard();

        while (board.checkWin() == null) {
            Integer[] pos;
            boolean inputValid = true;
            do {
                //Player inputs desired positon
                System.out.print("Enter position (x, y): ");
                inputValid = true;
                String[] position = stdin.nextLine().split(",");
                pos = new Integer[2];
                if(position.length < 2) {
                    inputValid = false;
                    continue;
                }
                try {
                    pos[0] = 2 - Integer.parseInt(position[1].trim());
                    pos[1] = Integer.parseInt(position[0].trim());
                } catch (NumberFormatException e) {
                    inputValid = false;
                }
                if(board.getgameBoard()[pos[0]][pos[1]] != Board.gameBoard.BLANK) {
                    inputValid = false;
                }
            } while(!inputValid);

            board.place(pos, Board.gameBoard.X);

            board.printBoard();
            System.out.println();
            if (board.checkWin() != null) {
                break;
            }
            //The AI performs it's move by calculating the best possible move at the moment using the minMaxingAlgorithm
            System.out.println("AI is playing...");
            board.place(bestMove(board), Board.gameBoard.O);
            board.printBoard();
        }

        System.out.println("=======================");
        System.out.println("Game Finished!");
        board.printBoard();
        if (board.checkWin() == AI) {
            System.out.println("AI WON!");
        } else if (board.checkWin() == player_1) {
            System.out.println("YOU WON?!");
        } else {
            System.out.println("TIE");
        }

    }

}

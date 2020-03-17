package com.tictactoe.source;

public class Artificial_Intelligence {
    //Defined every AI move to be O and every player move to be X
    public static Board.gameBoard AI = Board.gameBoard.O;
    public static Board.gameBoard player_1 = Board.gameBoard.X;

    //Stores all the possible best moves the ai could make
    public static Integer[] bestMove(Board board){
        Integer[] bestMove = null;
        int checkScore = -100;

        for(Integer[] placement: board.getAvailablePositions()){
            board.place(placement, AI);
            int score = minmaxAlgorithim(board);
            board.place(placement, Board.gameBoard.BLANK);
            if(score > checkScore){
                checkScore = score;
                bestMove = placement;
            }
        }
        return bestMove;
    }

    /*
    The artificial intelligence utilizes the Min-Max Algorithm
    in order to choose the most optimal move it should make at
    all times out of all of the bestMove's calculated
    */

    public static int minmaxAlgorithim(Board board){
        Board.gameBoard winner = board.checkWin();
        int score, AI_counter = 0, player_1_counter = 0, bestScore;
        if(winner != null){
            if(winner == AI){
                score = 1;
                return score;
            }
            else if(winner == Board.gameBoard.BLANK){
                score = 0;
                return score;
            }
            else{
                score = -1;
                return score;
            }
        }

        for(int i = 0; i < board.getgameBoard().length; i++){
            for(int j = 0; j < board.getgameBoard()[i].length; j++){
                if(board.getgameBoard()[i][j] == AI){
                    AI_counter++;
                }
                else if(board.getgameBoard()[i][j] == player_1){
                    player_1_counter++;
                }
            }
        }

        if(player_1_counter > AI_counter){
            bestScore = -1;
        }
        else{
            bestScore = 1;
        }

        for(Integer[] position: board.getAvailablePositions()){
            board.place(position, player_1_counter > AI_counter ? AI : player_1);
            int current_score = minmaxAlgorithim(board);
            board.place(position, Board.gameBoard.BLANK);
            if(player_1_counter > AI_counter ? current_score > bestScore : current_score < bestScore){
                bestScore = current_score;
            }
        }

        return bestScore;
    }

}

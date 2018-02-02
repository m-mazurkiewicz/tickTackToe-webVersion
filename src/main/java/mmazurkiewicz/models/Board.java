//package mmazurkiewicz.models;
//
//import mmazurkiewicz.controllers.InsertSignController;
//import mmazurkiewicz.forms.InsertSignForm;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Component
//public class Board{
//    private static final int DEFAULT_BOARD_WIDTH=3;
//    private static final int DEFAULT_BOARD_HEIGHT=3;
//    private int sizeX;
//    private int sizeY;
//    private ArrayList<ArrayList<Mark>> board;
//    private Mark currentPlayer;
//    private int movesCounter;
//    private int maxMovesPerGame;
//    //private InsertSignController insertSignController;
//    private boolean gameWin;
//    private boolean boardFull;
//
//    public Board(){
//        this(DEFAULT_BOARD_WIDTH,DEFAULT_BOARD_HEIGHT);
//    }
//
//    public Board(int x, int y) {
//        sizeX=x;
//        sizeY=y;
//        board = new ArrayList<>();
//        for (int i = 0; i < x; i++) {
//            board.add(i,new ArrayList<>()); //= new Mark[y];
//            for (int j = 0; j < y; j++) {
//                board.get(i).add(j,Mark.EMPTY);// = Mark.EMPTY;
//            }
//        }
//        movesCounter = 0;
//        maxMovesPerGame = sizeX * sizeY;
////        updateBoard();
//        currentPlayer = Mark.CIRCLE;
//        gameWin = false;
//        boardFull = false;
//    }
////
////    public void setGameListener(InsertSignController insertSignController) {
////        this.insertSignController= insertSignController;
////        gameListener.boardUpdated(board);
////        gameListener.playerMove(currentPlayer);
////    }
//
//    public int getSizeX() {
//        return sizeX;
//    }
//
//    public int getSizeY() {
//        return sizeY;
//    }
//
//    public ArrayList<Mark> getRow(int x){
//        return board.get(x);
//    }
//
//    public Mark getMark(int x, int y){
//        return board.get(x).get(y);
//    }
//
//    private void playerMove(int x, int y){
//        insertSign(x, y);
//    }
//
//    private void insertSign(int x, int y) {
//        if (checkIfRowExists(x) & checkIfColumnExists(y)) {
//            tryInsertPlayersMarkIntoField(x, y);
//        }
//       /* else {
//            actionWhenFieldIsOutsideBoard(x, y);
//        }*/
//    }
//
//    private void tryInsertPlayersMarkIntoField(int x, int y){
//        if (board.get(x).get(y) == Mark.EMPTY) {
//            board.get(x).set(y,currentPlayer);
//            movesCounter++;
//            isGameOver(x, y);
//        }
//        /*else {
//            actionWhenFieldIsOccupied(x, y);
//        }*/
//    }
//
//    private void isGameOver(int x, int y){
//        if (checkIfWin(x, y)) {
//            gameWin = true;
//        }
//        else {
//            isBoardFull();
//        }
//    }
//
//    private void isBoardFull() {
//        if(maxMovesPerGame <= movesCounter){
//            boardFull = true;
//        }
//        else {
//            changeCurrentPlayer();
//            //gameListener.playerMove(currentPlayer);
//        }
//    }
//
//    public void changeMark(int x, int y, Mark mark){
//        board.get(x).set(y, mark);
//    }
//
//    public void saveForm(InsertSignForm insertSignForm){
//        playerMove(insertSignForm.getX()-1, insertSignForm.getY()-1);
//    }
//
//    private void changeCurrentPlayer() {
//        currentPlayer = currentPlayer == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
//    }
//
//    public boolean isGameWin(){
//        return gameWin;
//    }
//
//    public boolean isBoardFilled(){
//        return boardFull;
//    }
//
//    public Mark getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    private boolean checkIfRowExists(int x) {
//        return (x >= 0 & x < sizeX);
//    }
//
//    private boolean checkIfColumnExists(int y) {
//        return (y >= 0 & y < sizeY);
//    }
//
//    private boolean isPlayerMarkEqualToInserted(int x, int y, Mark player) {
//        return board.get(x).get(y) == player;
//    }
//
//    private boolean checkIfRowHasWinningCombination(int x, int y) {
//        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x , y - 1, x, y - 2 )
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x, y - 1, x, y - 2)
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x, y + 1, x, y + 2);
//    }
//
//    private boolean checkIfColumnHasWinningCombination(int x, int y) {
//        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1 , y, x - 2, y)
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y, x + 1, y)
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y, x + 2, y);
//    }
//
//    private boolean checkIf1stDiagonalHasWinningCombination(int x, int y) {
//        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x -1 , y - 1, x - 2, y - 2 )
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y - 1, x - 2, y - 2)
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y + 1, x + 2, y + 2);
//    }
//
//    private boolean checkIf2ndDiagonalHasWinningCombination(int x, int y) {
//        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1 , y + 1, x - 2, y + 2 )
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y + 1, x + 1, y - 1)
//                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y - 1, x + 2, y - 2);
//    }
//
//    private boolean checkIfTwoFieldsHaveTheSameMarkAsInserted(int firstRowNumber, int firstColNumber, int secondRowNumber, int secondColNumber){
//        return  (checkIfFieldExistAndPlayerMarkIsEqualToInserted(firstRowNumber, firstColNumber)
//                && checkIfFieldExistAndPlayerMarkIsEqualToInserted(secondRowNumber, secondColNumber));
//    }
//
//    private boolean checkIfFieldExistAndPlayerMarkIsEqualToInserted(int x, int y){
//        return checkIfRowExists(x) & checkIfColumnExists(y)
//                && isPlayerMarkEqualToInserted(x, y, currentPlayer);
//    }
//
//    private boolean checkIfWin(int x, int y) {
//        return checkIfRowHasWinningCombination(x, y)
//                || checkIfColumnHasWinningCombination(x, y)
//                || checkIf1stDiagonalHasWinningCombination(x, y)
//                || checkIf2ndDiagonalHasWinningCombination(x, y);
//    }
//}

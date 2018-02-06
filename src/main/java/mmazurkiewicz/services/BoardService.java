package mmazurkiewicz.services;

import mmazurkiewicz.models.Board;
import mmazurkiewicz.models.Mark;

import java.util.ArrayList;

public interface BoardService {
    ArrayList<Board> getBoard();
    boolean insertSign(int rowNumber, int columnNumber);
    boolean isBoardFilled();
    void changeBoardSize(int rows, int columns);
    void changeCurrentPlayer();
    boolean checkIfWin(int rowNumber, int columnNumber);
    Mark getCurrentPlayer();
    int getMovesCounter();
    int getNumberOfRows();
    int getNumberOfColumns();
    boolean isGameOver();
}

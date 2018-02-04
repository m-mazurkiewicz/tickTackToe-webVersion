package mmazurkiewicz.services;

import mmazurkiewicz.models.Board2;
import mmazurkiewicz.models.Mark;

import java.util.ArrayList;

public interface BoardService {
    ArrayList<Board2> getBoard();
    void insertSign(int rowNumber, int columnNumber);
    boolean isBoardFilled();
    void changeBoardSize(int rows, int columns);
    void changeCurrentPlayer();
    boolean checkIfWin(int rowNumber, int columnNumber);
    Mark getCurrentPlayer();
}

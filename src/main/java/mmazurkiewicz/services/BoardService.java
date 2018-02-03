package mmazurkiewicz.services;

import mmazurkiewicz.models.Board2;

import java.util.ArrayList;

public interface BoardService {
    ArrayList<Board2> getBoard();
    void insertSign(int rowNumber, int columnNumber);
    boolean isBoardFilled();
    void changeBoardSize(int rows, int columns);
}

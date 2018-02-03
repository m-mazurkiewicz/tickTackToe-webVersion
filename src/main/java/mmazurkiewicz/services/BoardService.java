package mmazurkiewicz.services;

import mmazurkiewicz.models.Board2;

import java.util.ArrayList;

public interface BoardService {
    public ArrayList<Board2> getBoard();
    public void insertSign(int rowNumber, int columnNumber);
    public boolean isBoardFilled();
}

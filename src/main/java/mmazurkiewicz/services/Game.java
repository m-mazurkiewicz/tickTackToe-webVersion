package mmazurkiewicz.services;

import mmazurkiewicz.models.Board;
import mmazurkiewicz.models.Mark;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//@Service
public class Game {
    private Board board;

    public Game(){
        board = new Board();
    }

    public ArrayList<Mark> getRow(int x){
        return board.getRow(x);
    }
}

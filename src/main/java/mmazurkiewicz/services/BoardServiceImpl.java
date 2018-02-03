package mmazurkiewicz.services;

import mmazurkiewicz.models.Board2;
import mmazurkiewicz.models.Mark;
import mmazurkiewicz.repositories.RowsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Long.valueOf;

@Service
public class BoardServiceImpl implements BoardService {

    private final RowsRepository rowsRepository;
    private Mark player;
    public static final int maxMovesPerGame = 9;
    private int movesCounter;

    public BoardServiceImpl(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
        this.player = Mark.CIRCLE;
        this.movesCounter = 0;
        //maxMovesPerGame = getBoard().size() * getBoard().get(1).getColumns().size();
    }

    @Override
    public ArrayList<Board2> getBoard() {
        ArrayList<Board2> board = new ArrayList<>();
        rowsRepository.findAll().iterator().forEachRemaining(board::add);
        return board;
    }

    @Override
    public void insertSign(int rowNumber, int columnNumber) {
        Optional<Board2> optional = rowsRepository.findById(valueOf(rowNumber));

        if (!optional.isPresent()){
            throw new RuntimeException("Recipe is not present");
        }

        Board2 board2 = optional.get();
        board2.getColumns().set(columnNumber,player);
        player = player == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
        movesCounter++;
        rowsRepository.save(board2);
    }

    @Override
    public boolean isBoardFilled() {
        return movesCounter>=maxMovesPerGame;
    }
}

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
    private Mark currentPlayer;
    public static final int maxMovesPerGame = 9;
    private int movesCounter;

    public BoardServiceImpl(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
        this.currentPlayer = Mark.CIRCLE;
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
        board2.getColumns().set(columnNumber, currentPlayer);
        currentPlayer = currentPlayer == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
        movesCounter++;
        rowsRepository.save(board2);
    }

    @Override
    public boolean isBoardFilled() {
        return movesCounter>=maxMovesPerGame;
    }

    @Override
    public void changeBoardSize(int rows, int columns) {
        rowsRepository.deleteAll(); //todo: Zrobić tak, żeby id zaczynało się od 1 po zmianie, albo ewentualnie upewnieć się, że to w niczym nie przeszkadza
        ArrayList<Board2> board2 = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Mark> fields = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                fields.add(Mark.EMPTY);
            }
            Board2 newRow = new Board2(fields);
            board2.add(newRow);
        }
        rowsRepository.saveAll(board2);
    }
}

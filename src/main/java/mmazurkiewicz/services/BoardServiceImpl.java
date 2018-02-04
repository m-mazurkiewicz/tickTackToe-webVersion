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
    private int maxMovesPerGame;
    private int movesCounter;
    private int numberOfRows;
    private int numberOfColumns;

    public BoardServiceImpl(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
        this.currentPlayer = Mark.CIRCLE;
        this.movesCounter = 0;
        this.numberOfRows = 3;//getBoard().size();
        this.numberOfColumns = 3;//getBoard().get(1).getColumns().size();
        this.maxMovesPerGame = numberOfRows * numberOfColumns;
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
            throw new RuntimeException("Field is not present");
        }

        Board2 board2 = optional.get();
        board2.getColumns().set(columnNumber, currentPlayer);
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

    @Override
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
    }

    @Override
    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    private boolean isPlayerMarkEqualToInserted(int rowNumber, int columnNumber, Mark player) {
        Optional<Board2> optional = rowsRepository.findById(valueOf(rowNumber));

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Board2 board2 = optional.get();

        return board2.getColumns().get(columnNumber) == player;
    }

    private boolean checkIfRowHasWinningCombination(int rowNumber, int columnNumber) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber , columnNumber - 1, rowNumber, columnNumber - 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber, columnNumber - 1, rowNumber, columnNumber - 2)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber, columnNumber + 1, rowNumber, columnNumber + 2);
    }

    private boolean checkIfColumnHasWinningCombination(int rowNumber, int columnNumber) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber - 1 , columnNumber, rowNumber - 2, columnNumber)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber - 1, columnNumber, rowNumber + 1, columnNumber)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber + 1, columnNumber, rowNumber + 2, columnNumber);
    }

    private boolean checkIf1stDiagonalHasWinningCombination(int rowNumber, int columnNumber) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber -1 , columnNumber - 1, rowNumber - 2, columnNumber - 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber - 1, columnNumber - 1, rowNumber - 2, columnNumber - 2)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber + 1, columnNumber + 1, rowNumber + 2, columnNumber + 2);
    }

    private boolean checkIf2ndDiagonalHasWinningCombination(int rowNumber, int columnNumber) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber - 1 , columnNumber + 1, rowNumber - 2, columnNumber + 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber - 1, columnNumber + 1, rowNumber + 1, columnNumber - 1)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(rowNumber + 1, columnNumber - 1, rowNumber + 2, columnNumber - 2);
    }

    private boolean checkIfTwoFieldsHaveTheSameMarkAsInserted(int firstRowNumber, int firstColNumber, int secondRowNumber, int secondColNumber){
        return  (checkIfFieldExistAndPlayerMarkIsEqualToInserted(firstRowNumber, firstColNumber)
                && checkIfFieldExistAndPlayerMarkIsEqualToInserted(secondRowNumber, secondColNumber));
    }

    private boolean checkIfFieldExistAndPlayerMarkIsEqualToInserted(int x, int y){
        return checkIfRowExists(x) & checkIfColumnExists(y) &&
                isPlayerMarkEqualToInserted(x, y, currentPlayer);
    }

    private boolean checkIfRowExists(int rowNumber) {
        return (rowNumber >= 1 & rowNumber < numberOfRows + 1);
    }

    private boolean checkIfColumnExists(int columnNumber) {
        return (columnNumber >= 0 & columnNumber < numberOfColumns);
    }

    public boolean checkIfWin(int x, int y) {
        return checkIfRowHasWinningCombination(x, y)
                || checkIfColumnHasWinningCombination(x, y)
                || checkIf1stDiagonalHasWinningCombination(x, y)
                || checkIf2ndDiagonalHasWinningCombination(x, y);
    }
}

package mmazurkiewicz.services;

import mmazurkiewicz.models.Board;
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
    private int idOfFirstRow;

    public BoardServiceImpl(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
        this.currentPlayer = Mark.CIRCLE;
        this.movesCounter = 0;
        this.numberOfRows = 3;//getBoard().size();
        this.numberOfColumns = 3;//getBoard().get(1).getColumns().size();
        this.maxMovesPerGame = numberOfRows * numberOfColumns;
        this.idOfFirstRow = 1;
    }

    @Override
    public ArrayList<Board> getBoard() {
        ArrayList<Board> board = new ArrayList<>();
        rowsRepository.findAll().iterator().forEachRemaining(board::add);
        return board;
    }

    @Override
    public boolean insertSign(int rowNumber, int columnNumber) {
        if (!isFieldEmpty(rowNumber, columnNumber)){
            return false;
        }

        rowNumber = rowNumber + idOfFirstRow - 1;
        Optional<Board> optional = rowsRepository.findById(valueOf(rowNumber));

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Board board = optional.get();
        board.getColumns().set(columnNumber, currentPlayer);
        movesCounter++;
        rowsRepository.save(board);

        return true;
    }

    @Override
    public boolean isBoardFilled() {
        return movesCounter >= maxMovesPerGame;
    }

    @Override
    public void changeBoardSize(int rows, int columns) {
        rowsRepository.deleteAll();
        ArrayList<Board> board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Mark> fields = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                fields.add(Mark.EMPTY);
            }
            Board newRow = new Board(fields);
            board.add(newRow);
        }
        rowsRepository.saveAll(board);

        idOfFirstRow = idOfFirstRow + numberOfRows;   //todo: ewentualnie zaimplementować to lepiej
        numberOfRows = rows;
        numberOfColumns = columns;
        maxMovesPerGame = rows * columns;
        this.currentPlayer = Mark.CIRCLE;
        this.movesCounter = 0;
    }

    @Override
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
    }

    @Override
    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public int getMovesCounter() {
        return movesCounter;
    }

    @Override
    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Override
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    private boolean isFieldEmpty(int rowNumber, int columnNumber){
        return isPlayerMarkEqualToInserted(rowNumber, columnNumber, Mark.EMPTY);
    }

    private boolean isPlayerMarkEqualToInserted(int rowNumber, int columnNumber, Mark player) {
        rowNumber = rowNumber + idOfFirstRow - 1;
        Optional<Board> optional = rowsRepository.findById(valueOf(rowNumber));

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Board board = optional.get();

        return board.getColumns().get(columnNumber) == player;
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

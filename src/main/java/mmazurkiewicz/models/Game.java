package mmazurkiewicz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameNumber;
    private int numberOfRows;
    private int numberOfColumns;
    private int movesCounter;
    private Mark currentPlayer;
    private Long idOfFirstRow;

    public Long getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Long gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public void setMovesCounter(int movesCounter) {
        this.movesCounter = movesCounter;
    }

    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Mark currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Long getIdOfFirstRow() {
        return idOfFirstRow;
    }

    public void setIdOfFirstRow(Long idOfFirstRow) {
        this.idOfFirstRow = idOfFirstRow;
    }
}

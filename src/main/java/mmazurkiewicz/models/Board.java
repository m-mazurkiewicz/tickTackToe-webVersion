package mmazurkiewicz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowNumber;

    private ArrayList<Mark> columns;

    public Board() {
    }

    public Board(ArrayList<Mark> rowNumber) {
        this.columns = rowNumber;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public ArrayList<Mark> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Mark> columns) {
        this.columns = columns;
    }
}

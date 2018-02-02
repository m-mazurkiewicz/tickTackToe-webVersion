package mmazurkiewicz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Board2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long row;

    private ArrayList<Mark> columns;

    public Board2() {
    }

    public Board2(ArrayList<Mark> row) {
        this.columns = row;
    }

    public Long getRow() {
        return row;
    }

    public void setRow(Long row) {
        this.row = row;
    }

    public ArrayList<Mark> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Mark> columns) {
        this.columns = columns;
    }
}

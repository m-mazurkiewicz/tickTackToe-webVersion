package mmazurkiewicz.commands;

import mmazurkiewicz.models.Mark;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BoardCommand {

    private Long row;
    private ArrayList<Mark> columns = new ArrayList<>();

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

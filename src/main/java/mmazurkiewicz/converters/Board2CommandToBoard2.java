package mmazurkiewicz.converters;

import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import mmazurkiewicz.commands.Board2Command;
import mmazurkiewicz.models.Board2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Board2CommandToBoard2 implements Converter<Board2Command, Board2> {

    @Synchronized
    @Nullable
    @Override
    public Board2 convert(Board2Command rowsCommand) {
        if (rowsCommand == null){
            return null;
        }

        Board2 rows = new Board2();
        rows.setRow(rowsCommand.getRow());
        rows.setColumns(rowsCommand.getColumns());
        return rows;
    }
}

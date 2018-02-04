package mmazurkiewicz.converters;

import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import mmazurkiewicz.commands.BoardCommand;
import mmazurkiewicz.models.Board;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoardCommandToBoard implements Converter<BoardCommand, Board> {

    @Synchronized
    @Nullable
    @Override
    public Board convert(BoardCommand rowsCommand) {
        if (rowsCommand == null){
            return null;
        }

        Board rows = new Board();
        rows.setRowNumber(rowsCommand.getRow());
        rows.setColumns(rowsCommand.getColumns());
        return rows;
    }
}

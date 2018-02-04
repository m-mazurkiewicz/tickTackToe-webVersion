package mmazurkiewicz.converters;

import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import mmazurkiewicz.commands.BoardCommand;
import mmazurkiewicz.models.Board;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoardToBoardCommand implements Converter<Board, BoardCommand> {

    @Synchronized
    @Nullable
    @Override
    public BoardCommand convert(Board rows) {
        if (rows == null){
            return null;
        }

        final BoardCommand command = new BoardCommand();
        command.setRow(rows.getRowNumber());
        command.setColumns(rows.getColumns());
        return command;
    }
}

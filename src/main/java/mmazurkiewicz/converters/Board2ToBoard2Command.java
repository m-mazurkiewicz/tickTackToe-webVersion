package mmazurkiewicz.converters;

import com.sun.istack.internal.Nullable;
import lombok.Synchronized;
import mmazurkiewicz.commands.Board2Command;
import mmazurkiewicz.models.Board2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Board2ToBoard2Command implements Converter<Board2, Board2Command> {

    @Synchronized
    @Nullable
    @Override
    public Board2Command convert(Board2 rows) {
        if (rows == null){
            return null;
        }

        final Board2Command command = new Board2Command();
        command.setRow(rows.getRowNumber());
        command.setColumns(rows.getColumns());
        return command;
    }
}

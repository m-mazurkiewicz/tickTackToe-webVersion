package mmazurkiewicz.bootstrap;

import mmazurkiewicz.models.Mark;
import mmazurkiewicz.models.Board2;
import mmazurkiewicz.repositories.RowsRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RowsRepository rowsRepository;

    public DevBootstrap(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (int i = 0; i<=2; i++) {
            rowsRepository.save(getRows());
        }
    }

    private Board2 getRows(){

        ArrayList<Mark> fields = new ArrayList<>();
        fields.add(Mark.EMPTY);
        fields.add(Mark.EMPTY);
        fields.add(Mark.EMPTY);
        Board2 rows = new Board2(fields);
        int i;
        //for (i = 0; i<=2; i++){
        //    board.add(rows);
        //}
        return rows;
    }
}

package mmazurkiewicz.services;

import mmazurkiewicz.models.Board2;
import mmazurkiewicz.repositories.RowsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardServiceImpl implements BoardService {

    private final RowsRepository rowsRepository;

    public BoardServiceImpl(RowsRepository rowsRepository) {
        this.rowsRepository = rowsRepository;
    }

    @Override
    public ArrayList<Board2> getBoard() {
        ArrayList<Board2> board = new ArrayList<>();
        rowsRepository.findAll().iterator().forEachRemaining(board::add);
        return board;
    }
}

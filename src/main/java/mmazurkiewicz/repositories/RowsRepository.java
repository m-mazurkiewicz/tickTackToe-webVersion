package mmazurkiewicz.repositories;

import mmazurkiewicz.models.Board;
import org.springframework.data.repository.CrudRepository;

public interface RowsRepository extends CrudRepository<Board, Long> {
}

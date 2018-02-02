package mmazurkiewicz.repositories;

import mmazurkiewicz.models.Board2;
import org.springframework.data.repository.CrudRepository;

public interface RowsRepository extends CrudRepository<Board2, Long> {
}

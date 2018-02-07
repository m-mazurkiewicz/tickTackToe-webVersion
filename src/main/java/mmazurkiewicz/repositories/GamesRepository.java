package mmazurkiewicz.repositories;

import mmazurkiewicz.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GamesRepository extends CrudRepository<Game, Long> {
}

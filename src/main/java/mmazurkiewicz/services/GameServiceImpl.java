package mmazurkiewicz.services;

import mmazurkiewicz.models.Game;
import mmazurkiewicz.models.Mark;
import mmazurkiewicz.repositories.GamesRepository;

import java.util.Optional;

public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;

    public GameServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void newGame(int numberOfRows, int numberOfColumns) {
        Game game = new Game();

        game.setNumberOfRows(numberOfRows);
        game.setNumberOfColumns(numberOfColumns);

        gamesRepository.save(game);
    }

    @Override
    public void saveGame(Long id, int movesCounter, Mark currentPlayer) {
            Optional<Game> optional = gamesRepository.findById(id);

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Game game = optional.get();

        game.setCurrentPlayer(currentPlayer);
        game.setMovesCounter(movesCounter);

        gamesRepository.save(game);
    }

    @Override
    public void loadGame(Long id) {
        Optional<Game> optional = gamesRepository.findById(id);

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Game game = optional.get();
    }
}

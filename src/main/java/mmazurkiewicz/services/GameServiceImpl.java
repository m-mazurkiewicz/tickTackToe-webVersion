package mmazurkiewicz.services;

import mmazurkiewicz.models.Game;
import mmazurkiewicz.models.Mark;
import mmazurkiewicz.repositories.GamesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    private Long currentGame;

    public GameServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void newGame(int numberOfRows, int numberOfColumns) {
        Game game = new Game();

        game.setNumberOfRows(numberOfRows);
        game.setNumberOfColumns(numberOfColumns);

        gamesRepository.save(game);
        //gamesRepository.findAll().iterator().forEachRemaining();
    }

    @Override
    public void saveGame(Long id, int movesCounter, Mark currentPlayer) {
        Optional<Game> optional = gamesRepository.findById(id);  //todo: zastanowić się, czy nie zrobić tak, żeby nie trzeba było podawać ID, tylko szło z current game

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        Game game = optional.get();

        currentGame = id;
        game.setCurrentPlayer(currentPlayer);
        game.setMovesCounter(movesCounter);

        gamesRepository.save(game);
    }

    @Override
    public Game loadGame(Long id) {
        Optional<Game> optional = gamesRepository.findById(id);

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        return optional.get();
    }
}

package mmazurkiewicz.services;

import mmazurkiewicz.models.Game;
import mmazurkiewicz.repositories.GamesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    //private final BoardService boardService;
    private Long currentGame;

    public GameServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
       // this.boardService = boardService;
    }

    @Override
    public void newGame(int numberOfRows, int numberOfColumns) {
        Game game = new Game();

        game.setNumberOfRows(numberOfRows);
        game.setNumberOfColumns(numberOfColumns);

        ArrayList<Game> games = new ArrayList<>();

        gamesRepository.findAll().iterator().forEachRemaining(games::add);
        currentGame = (long) games.size() + 1;

        gamesRepository.save(game);
    }

    @Override
    public void saveGame(Game game) {
//        Optional<Game> optional = gamesRepository.findById(currentGame);
//
//        if (!optional.isPresent()){
//            throw new RuntimeException("Field is not present");
//        }

        //Game savedGame = boardService.saveGame(game);

        gamesRepository.save(game);
    }

    @Override
    public Game getCurrentGame() {
        Optional<Game> optional = gamesRepository.findById(currentGame);

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        return optional.get();
    }

    @Override
    public Game loadGame(Long id) {
        Optional<Game> optional = gamesRepository.findById(id);

        if (!optional.isPresent()){
            throw new RuntimeException("Field is not present");
        }

        currentGame = id;

        return optional.get();
    }
}

package mmazurkiewicz.services;

import mmazurkiewicz.models.Game;
import mmazurkiewicz.models.Mark;

public interface GameService {
    void newGame(int numberOfRows, int numberOfColumns);
    void saveGame(Game game);
    Game getCurrentGame();  //tylko tymczasowo
    Game loadGame(Long id);
}

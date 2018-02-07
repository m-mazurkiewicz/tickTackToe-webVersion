package mmazurkiewicz.services;

import mmazurkiewicz.models.Game;
import mmazurkiewicz.models.Mark;

public interface GameService {
    void newGame(int numberOfRows, int numberOfColumns);
    void saveGame(Long id, int movesCounter, Mark currentPlayer);
    Game loadGame(Long id);
}

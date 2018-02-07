package mmazurkiewicz.services;

import mmazurkiewicz.models.Mark;

public interface GameService {
    void newGame(int numberOfRows, int numberOfColumns);
    void saveGame(Long id, int movesCounter, Mark currentPlayer);
    void loadGame(Long id);
}

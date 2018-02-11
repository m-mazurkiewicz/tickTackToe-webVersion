package mmazurkiewicz.services;

public interface GameService {
    void newGame(int numberOfRows, int numberOfColumns);
    void saveGame();
    void loadGame(Long id);
}

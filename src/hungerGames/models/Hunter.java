package hungerGames.models;
import hungerGames.strategys.Move;

public class Hunter extends Player{
    public Hunter(String name, int x, int y, Move strategy) {
        super(name, "Hunter", 100, x, y, strategy);
    }
}

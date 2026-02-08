package hungerGames.models;
import hungerGames.strategys.Move;

public class Prey extends Player{
    public Prey(String name, int x, int y, Move strategy) {
        super(name, "Prey", 50, x, y, strategy);
    }
}

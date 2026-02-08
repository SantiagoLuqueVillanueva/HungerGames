package hungerGames.models;
import hungerGames.strategys.Move;

public class Prey extends Player{
    public Prey(String name, int x, int y, Move strategy) {
        super(name, 50, "Prey", x, y, strategy);
    }
}

package hungerGames.models;
import hungerGames.strategys.Move;

public class Obstacle extends Player{
    public Obstacle(String name, int x, int y, Move Strategy){
        super(name, "Obstacle", 0, x, y, Strategy);
    }
}

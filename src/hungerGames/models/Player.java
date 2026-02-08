package hungerGames.models;
import hungerGames.strategys.Move;

public abstract class Player {
    protected String name;
    protected int vitality;
    protected String type;
    protected int x;
    protected int y;
    protected Move strategy;

    public Player(String name, int vitality, String type, int x, int y, Move strategy){
        this.name = name;
        this.vitality = vitality;
        this.type = type;
        this.x = x;
        this.y = y;
        this.strategy = strategy;
    }

    public void performMove(Board board){
        if (strategy != null) {
            strategy.move(this, board);
        }
    }

    public String getName() {
        return name;
    }
    public int getVitality() {
        return vitality;
    }
    public String getType() {
        return type;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}

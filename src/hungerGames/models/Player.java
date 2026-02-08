package hungerGames.models;
import hungerGames.strategys.Move;

public abstract class Player {
    protected String name;
    protected String type;
    protected int vitality;
    protected int x;
    protected int y;
    protected Move strategy;

    public Player(String name, String type, int vitality, int x, int y, Move strategy){
        this.name = name;
        this.type = type;
        this.vitality = vitality;
        this.x = x;
        this.y = y;
        this.strategy = strategy;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void performMove(Board board){
        this.strategy.move(this, board);
    }
}

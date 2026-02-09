package hungerGames;
import hungerGames.models.*;
import hungerGames.strategys.*;

public class HungerGames {
    private Board board;
    
    private Player hunter1;
    private Player hunter2;
    private Player hunter3;
    private Player hunter4;

    private Player prey1;
    private Player prey2;
    private Player prey3;
    private Player prey4;

    private Player obstacle1;
    private Player obstacle2;
    private Player obstacle3;
    private Player obstacle4;

    public HungerGames(){
        this.board = new Board(20, 20);

        Move hunterStrategy = new HunterMove();
        Move preyStrategy = new PreyMove();

        this.hunter1 = new Hunter("H1", (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);
        this.hunter2 = new Hunter("H2", (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);
        this.hunter3 = new Hunter("H3", (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);
        this.hunter4 = new Hunter("H4", (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);

        this.prey1 = new Prey("P1", (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);
        this.prey2 = new Prey("P2", (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);
        this.prey3 = new Prey("P3", (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);
        this.prey4 = new Prey("P4", (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);

        this.obstacle1 = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);
        this.obstacle2 = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);
        this.obstacle3 = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);
        this.obstacle4 = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);

        board.placePlayer(hunter1);
        board.placePlayer(hunter2);
        board.placePlayer(hunter3);
        board.placePlayer(hunter4);

        board.placePlayer(prey1);
        board.placePlayer(prey2);
        board.placePlayer(prey3);
        board.placePlayer(prey4);

        board.placePlayer(obstacle1);
        board.placePlayer(obstacle2);
        board.placePlayer(obstacle3);
        board.placePlayer(obstacle4);
    }

    private boolean isAlive(Player p) {
        return board.getGrid()[p.getY()][p.getX()] == p;
    }

    public void start () throws Exception{
        while (true) {
            board.cleanBoard(); 
            board.printBoard();

            if (isAlive(hunter1)) hunter1.performMove(board);
            if (isAlive(hunter2)) hunter2.performMove(board);
            if (isAlive(hunter3)) hunter3.performMove(board);
            if (isAlive(hunter4)) hunter4.performMove(board);

            if (isAlive(prey1)) prey1.performMove(board);
            if (isAlive(prey2)) prey2.performMove(board);
            if (isAlive(prey3)) prey3.performMove(board);
            if (isAlive(prey4)) prey4.performMove(board);

            Thread.sleep(600);
        }
    }
}

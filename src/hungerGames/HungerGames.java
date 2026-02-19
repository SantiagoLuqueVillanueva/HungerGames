package hungerGames;

import hungerGames.models.*;
import hungerGames.strategys.*;
import java.util.ArrayList;
import java.util.List;

public class HungerGames {
    private Board board;
    
    private List<Player> hunters;
    private List<Player> preys;
    private List<Player> obstacles;

    public HungerGames(int huntersCount, int preysCount, int obstaclesCount){
        this.board = new Board(20, 20);

        Move hunterStrategy = new HunterMove();
        Move preyStrategy = new PreyMove();

        this.hunters = new ArrayList<>();
        this.preys = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        
        for (int i = 0; i < huntersCount; i++) {
            String name = "H"; 
            Player h = new Hunter(name, (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);
            hunters.add(h);
            board.placePlayer(h);
        }

        for (int i = 0; i < preysCount; i++) {
            String name = "P";
            Player p = new Prey(name, (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);
            preys.add(p);
            board.placePlayer(p);
        }

        for (int i = 0; i < obstaclesCount; i++) {
            Player obs = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);
            obstacles.add(obs);
            board.placePlayer(obs);
        }
    }

    private boolean isAlive(Player p) {
        Player onBoard = board.getGrid()[p.getY()][p.getX()];
        return onBoard == p;
    }

    public void start() throws Exception {
        while (true) {
            board.cleanBoard(); 
            board.printBoard();
            
            for (Player h : hunters) {
                if (isAlive(h)) {
                    h.performMove(board);
                }
            }

            for (Player p : preys) {
                if (isAlive(p)) {
                    p.performMove(board);
                }
            }

            int huntersAlive = 0;
            int preysAlive = 0;

            for (Player h : hunters) {
                if (isAlive(h)) {
                    huntersAlive++;
                }
            }

            for (Player p : preys) {
                if (isAlive(p)) {
                    preysAlive++;
                }
            }
            
            if (preysAlive == 0) {
                board.cleanBoard();
                board.printBoard(); 
                
                System.out.println("\n===================================");
                System.out.println("   GAME OVER!");
                System.out.println("   Winners: HUNTERS");
                System.out.println("===================================");
                break;
            }

            if (huntersAlive == 0) {
                board.cleanBoard();
                board.printBoard();
                
                System.out.println("\n===================================");
                System.out.println("   GAME OVER!");
                System.out.println("   Winners: PREYS");
                System.out.println("===================================");
                break;
            }

            System.out.println("Hunters Alive: " + huntersAlive + " | Preys Alive: " + preysAlive);

            Thread.sleep(400);
        }
    }
}

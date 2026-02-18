package hungerGames;

import hungerGames.models.*;
import hungerGames.strategys.*;
import java.util.ArrayList; // Import for ArrayList
import java.util.List;      // Import for List interface

public class HungerGames {
    private Board board;
    
    // --- LISTS (ArrayList) ---
    private List<Player> hunters;
    private List<Player> preys;
    private List<Player> obstacles;

    // Constructor: receives the quantity of each type
    public HungerGames(int huntersCount, int preysCount, int obstaclesCount){
        this.board = new Board(20, 20);

        Move hunterStrategy = new HunterMove();
        Move preyStrategy = new PreyMove();

        // Initialize empty lists
        this.hunters = new ArrayList<>();
        this.preys = new ArrayList<>();
        this.obstacles = new ArrayList<>();

        // --- AUTOMATIC CREATION (Loops) ---
        
        // Create Hunters
        for (int i = 0; i < huntersCount; i++) {
            // We create the name "H" + number (e.g., H1, H2)
            String name = "H"; 
            Player h = new Hunter(name, (int)(Math.random() * 20), (int)(Math.random() * 20), hunterStrategy);
            hunters.add(h);       // Add to list
            board.placePlayer(h); // Add to board
        }

        // Create Preys
        for (int i = 0; i < preysCount; i++) {
            String name = "P";
            Player p = new Prey(name, (int)(Math.random() * 20), (int)(Math.random() * 20), preyStrategy);
            preys.add(p);
            board.placePlayer(p);
        }

        // Create Obstacles
        for (int i = 0; i < obstaclesCount; i++) {
            Player obs = new Obstacle("#", (int)(Math.random() * 20), (int)(Math.random() * 20), null);
            obstacles.add(obs);
            board.placePlayer(obs);
        }
    }

    // Helper method to check if player is still on the board
    private boolean isAlive(Player p) {
        // Get whoever is currently on that board cell
        Player onBoard = board.getGrid()[p.getY()][p.getX()];
        // Check if it is the EXACT same object
        return onBoard == p;
    }

    public void start() throws Exception {
        while (true) {
            board.cleanBoard(); 
            board.printBoard();

            // --- MOVEMENT LOOPS ---
            
            // Move Hunters
            for (Player h : hunters) {
                if (isAlive(h)) {
                    h.performMove(board);
                }
            }

            // Move Preys
            for (Player p : preys) {
                if (isAlive(p)) {
                    p.performMove(board);
                }
            }

            // --- SURVIVOR COUNT ---
            int huntersAlive = 0;
            int preysAlive = 0;

            // Count living Hunters
            for (Player h : hunters) {
                if (isAlive(h)) {
                    huntersAlive++;
                }
            }

            // Count living Preys
            for (Player p : preys) {
                if (isAlive(p)) {
                    preysAlive++;
                }
            }

            // --- WIN CONDITIONS ---
            
            // Case A: Hunters Win
            if (preysAlive == 0) {
                board.cleanBoard();
                board.printBoard(); 
                
                System.out.println("\n===================================");
                System.out.println("   GAME OVER!");
                System.out.println("   Winners: HUNTERS");
                System.out.println("===================================");
                break;
            }

            // Case B: Preys Win
            if (huntersAlive == 0) {
                board.cleanBoard();
                board.printBoard();
                
                System.out.println("\n===================================");
                System.out.println("   GAME OVER!");
                System.out.println("   Winners: PREYS");
                System.out.println("===================================");
                break;
            }

            // Status update
            System.out.println("Hunters Alive: " + huntersAlive + " | Preys Alive: " + preysAlive);

            Thread.sleep(400);
        }
    }
}

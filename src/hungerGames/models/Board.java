package hungerGames.models;

public class Board {
    private Player[][] grid;
    private int width;
    private int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        this.grid = new Player[height][width];
    }

    public Player[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void placePlayer(Player p){
        int x = p.getX();
        int y = p.getY();

        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[y][x] = p;
        }
    }

    public void movePlayer(Player p, int newX, int newY){
        if ((newX < 0 || newX >= width) || (newY < 0 || newY >= height)) {
            return;
        }

        Player objective = grid[newY][newX];
        
        if (objective == null) {
            if (grid[p.getY()][p.getX()] == p) { 
                grid[p.getY()][p.getX()] = null;
            }
            
            p.setPosition(newX, newY);
            grid[newY][newX] = p;
        } 
        else {

            if (objective.getType().equals("Obstacle") || objective.getType().equals(p.getType())) {
                return; 
            }

            double prob = (double) p.getVitality() / (objective.getVitality() + p.getVitality());

            double random = Math.random();

            if (random < prob) {
                if (grid[p.getY()][p.getX()] == p) {
                    grid[p.getY()][p.getX()] = null;
                }
                p.setPosition(newX, newY);
                grid[newY][newX] = p;
            } else {

                if (grid[p.getY()][p.getX()] == p) {
                    grid[p.getY()][p.getX()] = null;
                }
            }
        }
    }

    public void printBoard() {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";

        System.out.print("+");
        for (int i = 0; i < width * 2; i++) {
            System.out.print("-"); 
        }
        System.out.println("+");

        for (int i = 0; i < height; i++) {
            System.out.print("|");

            for (int j = 0; j < width; j++) {
                Player p = grid[i][j];

                if (p == null) {
                    System.out.print("  "); 
                } else {
                    String color = RESET;
                    
                    if (p.getType().equals("Hunter")){
                        color = RED;
                    } else if (p.getType().equals("Prey")){
                        color = GREEN;
                    } 
                    else if (p.getType().equals("Obstacle")){
                        color = YELLOW;
                    } 

                    System.out.print(color + p.getName() + RESET + " ");
                }
            }
            
            System.out.println("|");
        }

        System.out.print("+");
        for (int k = 0; k < width * 2; k++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void cleanBoard() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

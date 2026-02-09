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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Player c = grid[i][j];
                if (c == null) {
                    System.out.print(".  ");
                } else {
                    System.out.print(c.getName().charAt(0) + "  ");
                }
            }
            System.out.println();
        }
    }

    public void cleanBoard() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

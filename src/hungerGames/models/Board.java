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

    public void moveCharacter(Player p, int newX, int newY){
        // Comprobaciones
        if ((newX < 0 || newX >= width) || (newY < 0 || newY >= height)) {
            return;
        }
        if (grid[newY][newX] != null) {
            return;
        }
        
        // Movimiento
        grid[p.getY()][p.getX()] = null;
        p.setPosition(newX, newY);
        grid[newY][newX] = p; 
    }

    public void printBoard() {
        System.out.println("--- GAME BOARD ---");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Player c = grid[y][x];
                if (c == null) {
                    System.out.print(".  "); // Casilla vacía
                } else {
                    // Imprimimos la primera letra del nombre
                    // Si es Hunter saldrá 'H', si es Prey saldrá 'P'
                    System.out.print(c.getName().charAt(0) + "  ");
                }
            }
            System.out.println(); // Salto de línea al terminar la fila
        }
        System.out.println("------------------");
    }
}

package hungerGames.models;

public class Board {
    private Player[][] grid;
    private int width;
    private int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        this.grid = new Player[width][height];
    }

    public Player[][] getGrid() {
        return grid;
    }

    public void placePlayer(Player c){
        int x = c.getX();
        int y = c.getY();

        grid[y][x] = c;
    }

    public void movePlayer(Player c, int newX, int newY) {
        // A. Validaciones de seguridad
        if (!isValidPosition(newX, newY)) return; // Si se sale del mapa, cancelamos.
        if (grid[newY][newX] != null) return;     // Si la casilla está ocupada, cancelamos (choque).

        // B. El movimiento real (Teletransporte)
        
        // Paso 1: Borrar al personaje de su casilla ANTIGUA
        int oldX = c.getX();
        int oldY = c.getY();
        grid[oldY][oldX] = null; // Dejamos el hueco vacío (null)

        // Paso 2: Actualizar las coordenadas internas del personaje
        c.setPosition(newX, newY);

        // Paso 3: Poner al personaje en la casilla NUEVA
        grid[newY][newX] = c;
    }

    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
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

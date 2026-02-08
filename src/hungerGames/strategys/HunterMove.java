package hungerGames.strategys;
import hungerGames.models.*;

public class HunterMove implements Move{
    private double calculateDistance(int x1, int x2, int y1, int y2){
        double h = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return h;
    }
    
    @Override
    public void move(Player me, Board board) {
        // Variables iniciales.
        Player aim = null;
        double minDistance = Double.MAX_VALUE;

        // Escáner
        Player[][] grid = board.getGrid();
        Player posiblePrey = null;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                posiblePrey = grid[i][j];
                // Comprobación de que es una presa
                if (posiblePrey != null && posiblePrey.getType().equals("Prey")) {
                    // Calculamos la distancia con esta presa
                    double distance = calculateDistance(me.getX(), me.getY(), posiblePrey.getX(), posiblePrey.getY());
                    // Si la presa nueva esta a menos distancia que la anterior, fijamos a esta
                    if (distance < minDistance) {
                        minDistance = distance;
                        aim = posiblePrey;
                    }
                }
            }
        }

        if (aim != null) {
            int currentX = me.getX();
            int currentY = me.getY();
            
            int nextX = currentX;
            int nextY = currentY;

            // --- EJE X ---
            if (aim.getX() > currentX) nextX++;
            else if (aim.getX() < currentX) nextX--;

            // CORRECCIÓN DE BORDES (Para que no se atasque en la pared)
            if (nextX < 0) nextX = 0; // Se queda en el borde
            else if (nextX >= board.getWidth()) nextX = board.getWidth() - 1;

            // --- EJE Y ---
            if (aim.getY() > currentY) nextY++;
            else if (aim.getY() < currentY) nextY--;

            // CORRECCIÓN DE BORDES
            if (nextY < 0) nextY = 0;
            else if (nextY >= board.getHeight()) nextY = board.getHeight() - 1;

            board.moveCharacter(me, nextX, nextY);
        }
    }
}

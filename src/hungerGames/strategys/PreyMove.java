package hungerGames.strategys;
import hungerGames.models.*;

public class PreyMove implements Move{
    private double calculateDistance(int x1, int x2, int y1, int y2){
        double h = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return h;
    }

    @Override
    public void move(Player p, Board board){
        // Variables iniciales
        Player hunter = null;
        double minDistance = Double.MAX_VALUE;

        // Scanner
        Player[][] grid = board.getGrid();
        Player posibleHunter = null;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                posibleHunter = grid[i][j];
                // Comprobación que es un hunter
                if (posibleHunter != null && posibleHunter.getType().equals("Hunter")) {
                    // Calculamos distancia
                    double distance = calculateDistance(p.getX(), posibleHunter.getX(), p.getY(), posibleHunter.getY());
                    // Si es menor reseteamo valores
                    if (distance < minDistance) {
                        hunter = posibleHunter;
                        minDistance = distance;
                    }
                }
            }
        }

        if (hunter != null) {
            int currentX = p.getX();
            int currentY = p.getY();
            
            // Calculamos el intento de movimiento
            int nextX = currentX;
            int nextY = currentY;

            // --- LÓGICA EJE X (Horizontal) ---
            if (hunter.getX() > currentX) {
                nextX--; // Huye a la izquierda
            } else {
                nextX++; // Huye a la derecha
            }

            // REBOTE X: Si me salgo del mapa, invierto el movimiento
            if (nextX < 0) {
                nextX = currentX + 1; // Rebotar hacia la derecha
            } else if (nextX >= board.getWidth()) {
                nextX = currentX - 1; // Rebotar hacia la izquierda
            }

            // --- LÓGICA EJE Y (Vertical) ---
            if (hunter.getY() > currentY) {
                nextY--; // Huye hacia arriba
            } else {
                nextY++; // Huye hacia abajo
            }

            // REBOTE Y
            if (nextY < 0) {
                nextY = currentY + 1; // Rebotar hacia abajo
            } else if (nextY >= board.getHeight()) {
                nextY = currentY - 1; // Rebotar hacia arriba
            }

            // Finalmente movemos
            board.moveCharacter(p, nextX, nextY);
        }
    }
}

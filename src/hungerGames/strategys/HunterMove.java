package hungerGames.strategys;
import hungerGames.models.Board;
import hungerGames.models.Player;
import hungerGames.models.Prey;

public class HunterMove implements Move{
    @Override
    public void move(Player me, Board board) {
        // 1. OBTENER INFORMACIÓN: ¿Dónde está la presa?
        Player target = findTarget(board);

        // Si encontramos una presa, nos movemos hacia ella
        if (target != null) {
            int newX = me.getX();
            int newY = me.getY();

            // Lógica Horizontal (Eje X)
            // Si el objetivo está a mi derecha (su X es mayor), sumo 1.
            if (target.getX() > me.getX()) {
                newX++; 
            } else if (target.getX() < me.getX()) {
                newX--; 
            }

            // Lógica Vertical (Eje Y)
            // Si el objetivo está abajo (su Y es mayor), sumo 1.
            if (target.getY() > me.getY()) {
                newY++;
            } else if (target.getY() < me.getY()) {
                newY--;
            }

            // 2. EJECUTAR ACCIÓN: Decirle al tablero que nos mueva
            board.movePlayer(me, newX, newY);
            
            // Mensaje para ver qué pasa en consola
            System.out.println(me.getName() + " (Hunter) persigue a la presa.");
        } else {
            System.out.println(me.getName() + " no ve ninguna presa.");
        }
    }

    // Método auxiliar para escanear la matriz
    private Player findTarget(Board board) {
        Player[][] grid = board.getGrid();

        // Recorremos fila por fila (Y)
        for (int y = 0; y < grid.length; y++) {
            // Recorremos columna por columna (X)
            for (int x = 0; x < grid[0].length; x++) {
                Player c = grid[y][x];
                
                // Si encontramos a alguien Y ese alguien es una PRESA
                if (c != null && c instanceof Prey) {
                    return c; // ¡Objetivo localizado! Devolvemos la presa encontrada.
                }
            }
        }
        return null; // No se encontró nada en todo el mapa
    }
}

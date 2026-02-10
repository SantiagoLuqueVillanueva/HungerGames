package hungerGames.strategys;
import hungerGames.models.*;

public class PreyMove implements Move {

    private double calculateDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    @Override
    public void move(Player p, Board board){
        Player hunter = null;
        double minDistance = Double.MAX_VALUE;
        Player[][] grid = board.getGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Player pos = grid[i][j];
                if (p != null && p.getType().equals("Hunter")) {
                    double distance = calculateDistance(p.getX(), p.getY(), pos.getX(), pos.getY());
                    if (distance < minDistance) {
                        hunter = pos;
                        minDistance = distance;
                    }
                }
            }
        }

        int currentX = p.getX();
        int currentY = p.getY();
        int nextX = currentX;
        int nextY = currentY;

        if (hunter != null) {
            if (hunter.getX() > currentX) 
                nextX--; 
            else 
                nextX++;

            if (hunter.getY() > currentY) 
                nextY--; 
            else 
                nextY++;

        } else {
            int dir = (int)(Math.random() * 4);
            if (dir == 0) 
                nextX++;
            else if (dir == 1) 
                nextX--;
            else if (dir == 2) 
                nextY++;
            else if (dir == 3) 
                nextY--;
        }
        
        if (nextX < 0) 
            nextX = currentX + 1;

        if (nextX >= board.getWidth()) 
            nextX = board.getWidth() - 1;

        if (nextY < 0) 
            nextY = currentY + 1;

        if (nextY >= board.getHeight()) 
            nextY = board.getHeight() - 1;

        Player nextCell = grid[nextY][nextX];
        if (nextCell != null && nextCell.getType().equals("Obstacle")) {
            nextX = currentX;
            nextY = currentY;
        }

        board.movePlayer(p, nextX, nextY);
    }
}

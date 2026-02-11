package hungerGames.strategys;
import hungerGames.models.*;

public class HunterMove implements Move {
    private double calculateDistance(int x1, int y1, int x2, int y2){ 
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }
    
    @Override
    public void move(Player p, Board board) {
        Player aim = null;
        double minDistance = Double.MAX_VALUE;
        Player[][] grid = board.getGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Player pos = grid[i][j];
                if (pos != null && pos.getType().equals("Prey")) {
                    double distance = calculateDistance(p.getX(), p.getY(), pos.getX(), pos.getY());
                    if (distance < minDistance) {
                        minDistance = distance;
                        aim = pos;
                    }
                }
            }
        }

        int currentX = p.getX();
        int currentY = p.getY();
        int nextX = currentX;
        int nextY = currentY;

        if (aim != null) {
            if (aim.getX() > currentX) 
                nextX++;
            else if (aim.getX() < currentX) 
                nextX--;

            if (aim.getY() > currentY) 
                nextY++;
            else if (aim.getY() < currentY) 
                nextY--;
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
        else if (nextX >= board.getWidth()) 
            nextX = board.getWidth() - 1;

        if (nextY < 0) 
            nextY = currentY + 1;
        else if (nextY >= board.getHeight()) 
            nextY = board.getHeight() - 1;

        Player nextCell = grid[nextY][nextX];
        if (nextCell != null && nextCell.getType().equals("Obstacle")) {
            if (nextX != currentX && nextY != currentY) {
                nextY = currentY;
            } else {
                nextX = currentX; nextY = currentY;
            }
        }

        board.movePlayer(p, nextX, nextY);
    }
}

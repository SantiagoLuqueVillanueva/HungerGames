package hungerGames;
import hungerGames.models.*;
import hungerGames.strategys.*;

public class HungerGames {
    private Board board;
    private Player hunter;
    private Player prey;

    public HungerGames(){
        this.board = new Board(10, 10);

        Move hunterStrategy = new HunterMove();
        Move preyStrategy = new PreyMove();

        this.hunter = new Hunter("H", 9, 0, hunterStrategy);
        this.prey = new Prey("P", 8, 2, preyStrategy);

        board.placePlayer(hunter);
        board.placePlayer(prey);
    }

    public void start(){
        while (true) {
            board.printBoard();
            System.out.println("DEBUG:");
            System.out.println(" - Cazador en: " + hunter.getX() + "," + hunter.getY());
            System.out.println(" - Presa en:   " + prey.getX() + "," + prey.getY());

            hunter.performMove(board);
            prey.performMove(board);

            try {
                Thread.sleep(1000); // 1000 milisegundos = 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace(); // Esto solo salta si forzamos el cierre del programa
            }
        }
    }
}

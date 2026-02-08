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
        this.prey = new Prey("P", 5, 5, preyStrategy);

        board.placePlayer(hunter);
        board.placePlayer(prey);
    }

    public void start(){
        while (true) {
            board.printBoard();

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

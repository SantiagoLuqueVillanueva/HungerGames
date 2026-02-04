package hungerGames.strategys;
import hungerGames.models.Player;
import hungerGames.models.Board;

public interface Move {
    void move(Player player, Board board);
}

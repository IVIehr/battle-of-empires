import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Player {
    Board board;
    int gridBoard;

    public Player(boolean enemy, int gridBoard, EventHandler<? super MouseEvent> eventHandler) {
        board = new Board(enemy, gridBoard, eventHandler);
        this.gridBoard = gridBoard;
    }

}

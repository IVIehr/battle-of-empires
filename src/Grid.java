import javafx.scene.control.Button;
import pieces.*;

public class Grid extends Button {
    public Piece piece = null;
    public boolean hasPiece = false;
    public int x, y;
    public Board board;
    public boolean wasShot;

    //create a grid to make up the board
    public Grid(int x, int y, Board board) {
        setStyle("-fx-background-color: #e2c07b;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 0;"
                + "-fx-border-color:#834214;");
        setMinWidth(35);
        setMinHeight(35);
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public void shoot(Piece piece, int x, int y) {
        if (piece.isAlive() && !board.grids[x][y].wasShot) {
            for (int i = x; i < x + piece.getStrengthWidth() && i < board.BOARD_SIZE; i++) {
                for (int j = y; j < y + piece.getStrengthHeight() && j < board.BOARD_SIZE; j++) {
                    if (board.grids[i][j].hasPiece) {
                        if (board.grids[i][j].piece instanceof Command && board.commands.size() > 0) {
                            int index = board.commands.size() - 1;
                            board.commands.get(index).hit();
                        }
                        if (board.grids[i][j].piece instanceof Rook && board.rooks.size() > 0) {
                            int index = board.rooks.size() - 1;
                            board.rooks.get(index).hit();
                        }
                        if (board.grids[i][j].piece instanceof Knight && board.knights.size() > 0) {
                            int index = board.knights.size() - 1;
                            board.knights.get(index).hit();
                        }
                        if (board.grids[i][j].piece instanceof Pawn && board.pawns.size() > 0) {
                            int index = board.pawns.size() - 1;
                            board.pawns.get(index).hit();
                        }

                        board.grids[i][j].setStyle("-fx-background-color: #e90c06;");
                    } else board.grids[i][j].setStyle("-fx-background-color: #54250e;" + "-fx-border-width: 2;");
                    board.grids[i][j].wasShot = true;
                }
            }
        }
        if (board.commands.size() > 0 && !board.commands.get(board.commands.size() - 1).isAlive()) {
            board.commands.remove(board.commands.size() - 1);
            board.pieceNumber--;
            board.collectCoin(30);
        }
        if (board.rooks.size() > 0 && !board.rooks.get(board.rooks.size() - 1).isAlive()) {
            board.rooks.remove(board.rooks.size() - 1);
            board.pieceNumber--;
            board.collectCoin(16);
        }
        if (board.knights.size() > 0 && !board.knights.get(board.knights.size() - 1).isAlive()) {
            board.knights.remove(board.knights.size() - 1);
            board.pieceNumber--;
            board.collectCoin(6);
        }
        if (board.pawns.size() > 0 && !board.pawns.get(board.pawns.size() - 1).isAlive()) {
            board.pawns.remove(board.pawns.size() - 1);
            board.pieceNumber--;
            board.collectCoin(2);
        }
    }


}


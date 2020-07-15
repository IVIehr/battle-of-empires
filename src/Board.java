import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pieces.*;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public final int BOARD_SIZE;
    public boolean enemy;
    public int pieceNumber = 11, commandRecovery, knightRecovery, rookRecovery, pawnRecovery;
    Random random = new Random();
    ArrayList<Pawn> pawns = new ArrayList<Pawn>();
    ArrayList<Knight> knights = new ArrayList<Knight>();
    ArrayList<Rook> rooks = new ArrayList<Rook>();
    ArrayList<Command> commands = new ArrayList<Command>();
    GridPane gridPane = new GridPane();
    Grid[][] grids;
    private int coin;

    public Board(boolean enemy, int size, EventHandler<? super MouseEvent> eventHandler) {
        this.enemy = enemy;
        BOARD_SIZE = size;
        grids = new Grid[BOARD_SIZE][BOARD_SIZE];
        gridPane.setPrefSize(500, 500);
        if (!enemy) {//left board
            gridPane.setAlignment(Pos.CENTER_LEFT);
            gridPane.setPadding(new Insets(0, 0, 0, 60));
        } else {//right board foe enemy
            gridPane.setAlignment(Pos.CENTER_RIGHT);
            gridPane.setPadding(new Insets(0, 60, 0, 0));
        }
        gridPane.setVgap(2);
        gridPane.setHgap(2);
        //creat board with grids
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Grid grid = new Grid(i, j, this);
                grids[i][j] = grid;
                grid.setOnMouseClicked(eventHandler);
                gridPane.add(grid, j, i);
            }
        }
    }

    public void collectCoin(int addCoin) {
        coin = coin + addCoin;
    }

    public int getCoin() {
        return coin;
    }

    public boolean canNotPlacePiece(Piece piece, int x, int y) {
        //prevent to place pieces out of board bounds
        if (piece instanceof Command && (x == BOARD_SIZE - 2 || x == BOARD_SIZE - 1 || y == BOARD_SIZE - 2 || y == BOARD_SIZE - 1))
            return true;
        else if (piece instanceof Rook && (x == BOARD_SIZE - 1 || y == BOARD_SIZE - 1)) return true;
        else if (piece instanceof Knight && x == BOARD_SIZE - 1) return true;
        else return grids[x][y].hasPiece || grids[x + 1][y].hasPiece;
    }

    public void placePiece(Piece piece, int x, int y) {
        int X = x;
        int Y = y;
        if (piece instanceof Pawn) {
            while (canNotPlacePiece(piece, X, Y)) {
                X = random.nextInt(BOARD_SIZE - 1);
                Y = random.nextInt(BOARD_SIZE - 1);
            }
            if (!enemy) grids[X][Y].setStyle("-fx-background-image: url('pawn-1.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            grids[X][Y].hasPiece = true;
            grids[X][Y].piece = piece;
            pawns.add((Pawn) piece);//add piece to arrayList
        }

        if (piece instanceof Rook) {
            while (canNotPlacePiece(piece, X, Y)) {
                X = random.nextInt(BOARD_SIZE - 1);
                Y = random.nextInt(BOARD_SIZE - 1);
            }
            if (!enemy) grids[X][Y].setStyle("-fx-background-image: url('rook-1.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X][Y + 1].setStyle("-fx-background-image: url('rook-2.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y].setStyle("-fx-background-image: url('rook-3.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y + 1].setStyle("-fx-background-image: url('rook-4.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    grids[X + i][Y + j].hasPiece = true;
                    grids[X + i][Y + j].piece = piece;
                }
            }
            rooks.add((Rook) piece);//add piece to arrayList
        }
        if (piece instanceof Command) {
            while (canNotPlacePiece(piece, X, Y)) {
                X = random.nextInt(BOARD_SIZE - 1);
                Y = random.nextInt(BOARD_SIZE - 1);
            }
            if (!enemy) grids[X][Y].setStyle("-fx-background-image: url('command-1.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X][Y + 1].setStyle("-fx-background-image: url('command-2.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X][Y + 2].setStyle("-fx-background-image: url('command-3.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y].setStyle("-fx-background-image: url('command-4.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y + 1].setStyle("-fx-background-image: url('command-5.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y + 2].setStyle("-fx-background-image: url('command-6.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 2][Y].setStyle("-fx-background-image: url('command-7.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 2][Y + 1].setStyle("-fx-background-image: url('command-8.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 2][Y + 2].setStyle("-fx-background-image: url('command-9.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");

            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    grids[X + i][Y + j].hasPiece = true;
                    grids[X + i][Y + j].piece = piece;
                }
            }
            commands.add((Command) piece);//add piece to arrayList
        }
        if (piece instanceof Knight) {
            while (canNotPlacePiece(piece, X, Y)) {
                X = random.nextInt(BOARD_SIZE - 1);
                Y = random.nextInt(BOARD_SIZE - 1);
            }
            if (!enemy) grids[X][Y].setStyle("-fx-background-image: url('knight-1.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            if (!enemy) grids[X + 1][Y].setStyle("-fx-background-image: url('knight-2.jpg');"
                    + "-fx-background-size: 35px;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-position: 90%;");
            grids[X][Y].hasPiece = true;
            grids[X + 1][Y].hasPiece = true;
            grids[X][Y].piece = piece;
            grids[X + 1][Y].piece = piece;
            knights.add((Knight) piece);//add piece to arrayList
        }
    }
}
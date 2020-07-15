package pieces;

public class Rook extends Piece {
    public static int countRook = 0;

    public Rook() {
        super(2, 2);
        setStrengthWidth(3);
        setStrengthHeight(5);
        setRecoveryTime(3);
        countRook++;
    }

    public static int getCountRook() {
        return countRook;
    }
}
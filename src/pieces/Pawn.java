package pieces;

public class Pawn extends Piece {
    public static int countPawn = 0;

    public Pawn() {
        super(1, 1);
        setStrengthWidth(1);
        setStrengthHeight(1);
        setRecoveryTime(1);
        countPawn++;
    }

    public static int getCountPawn() {
        return countPawn;
    }
}

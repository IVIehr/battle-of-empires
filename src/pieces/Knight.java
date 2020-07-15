package pieces;

public class Knight extends Piece {
    public static int countKnight = 0;

    public Knight() {
        super(2, 1);
        setStrengthWidth(3);
        setStrengthHeight(2);
        setRecoveryTime(2);
        countKnight++;
    }

    public static int getCountKnight() {
        return countKnight;
    }
}

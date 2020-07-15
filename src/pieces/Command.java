package pieces;

public class Command extends Piece {
    public static int countCommand = 0;

    public Command() {
        super(3, 3);
        setStrengthHeight(5);
        setStrengthWidth(5);
        setRecoveryTime(5);
        countCommand++;
    }

    public static int getCountCommand() {
        return countCommand;
    }
}



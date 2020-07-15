package pieces;

public class Piece {
    public final int width, height;
    private int health, strengthWidth, StrengthHeight, recoveryTime;

    public Piece(int width, int height) {
        this.width = width;
        this.height = height;
        health = width * height;
    }

    public int getStrengthHeight() {
        return StrengthHeight;
    }

    public void setStrengthHeight(int strengthHeight) {
        this.StrengthHeight = strengthHeight;
    }

    public int getStrengthWidth() {
        return strengthWidth;
    }

    public void setStrengthWidth(int strengthWidth) {
        this.strengthWidth = strengthWidth;
    }

    public int getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(int recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

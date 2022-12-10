package domain;

import java.util.Random;

public class Dice {

    private int point;
    private final Random random = new Random();

    public int getPoint() {
        return point;
    }

    public void throwDice() {
        this.point = random.nextInt(6) + 1;
    }
}

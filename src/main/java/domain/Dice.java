package domain;

import java.util.Random;

public class Dice {

    private int point;
    private static final Random RANDOM = new Random();

    public int throwDice() {
        return RANDOM.nextInt(6) + 1;
    }
}

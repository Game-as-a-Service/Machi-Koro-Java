package main.model;

import java.util.Random;

public class Dice {

    private int point;

    public int getPoint() {
        return point;
    }

    public void throwDice() {
        var random = new Random();
        this.point = random.nextInt(6) + 1;
    }
}

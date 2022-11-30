package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    @Test
    void throwDice() {
        for (int i = 0; i < 999; i++) {
            dice.throwDice();
            assertTrue(dice.getPoint() <= 6);
            assertTrue(dice.getPoint() >= 1);
        }
    }
}
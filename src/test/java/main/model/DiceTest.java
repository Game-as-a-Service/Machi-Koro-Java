package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
            assertThat(dice.getPoint() > 0).isTrue();
            assertThat(dice.getPoint() < 7).isTrue();
        }
    }
}
package domain;

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
            var point = dice.throwDice();
            assertThat(point > 0 && point < 7).isTrue();
        }
    }
}

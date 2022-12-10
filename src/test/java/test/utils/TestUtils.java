package test.utils;

import main.model.Bank;
import main.model.Game;
import main.model.Player;

import java.util.Arrays;

public class TestUtils {
    public static Game createTestGameWithFixedDiceValue(int dicePoint, int numberOfPlayer) {
        return new Game(new Bank(100), Arrays.asList(createPlayerArrayWithFixedNumber(numberOfPlayer)), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return dicePoint;
            }
        };
    }

    private static Player[] createPlayerArrayWithFixedNumber(int numberOfPlayer) {
        var players = new Player[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++)
            players[i] = new Player(String.valueOf(i + 1));

        return players;
    }
}

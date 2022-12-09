package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ForestTest {

    private Forest forest;
    Player player;
    Game game;

    @BeforeEach
    void setUp() {
        forest = new Forest();
        player = new Player("8hkibA9");
    }

    @Test
    void takeEffect() {
        game = new Game(new Bank(100), List.of(player), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 5;
            }
        };

        forest.takeEffect(game, player);
        var balanceOfPlayer = game.getPlayers().get(0).getTotalCoin();
        var balanceOfBank = game.getBank().getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(1);
        assertThat(balanceOfBank).isEqualTo(99);
    }
}
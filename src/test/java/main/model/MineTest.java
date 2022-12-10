package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class MineTest {

    private Mine mine;
    private Game game;

    @BeforeEach
    void setUp() {
        mine = new Mine();
    }

    @Test
    void takeEffect() {
        game = TestUtils.createTestGameWithFixedDiceValue(9, 1);
        var originalBalanceOfBank = game.getBank().getTotalCoin();
        var player = game.getPlayers().get(0);
        var originalBalanceOfPlayer = player.getTotalCoin();

        mine.takeEffect(game, player);

        var balanceOfBank = game.getBank().getTotalCoin();
        var balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 5);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 5);

    }
}
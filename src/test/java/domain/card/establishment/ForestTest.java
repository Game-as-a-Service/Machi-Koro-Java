package domain.card.establishment;

import domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class ForestTest {

    private Forest forest;
    Game game;

    @BeforeEach
    void setUp() {
        forest = new Forest();
    }

    @Test
    void takeEffect() {

        game = new TestGameBuilder().setFixedDicePoint(5).setPlayers(1).build();
        var player = game.getPlayers().get(0);
        var originalBalanceOfPlayer = player.getTotalCoin();
        var originalBalanceOfBank = game.getBank().getTotalCoin();

        forest.takeEffect(game, player);

        var balanceOfPlayer = game.getPlayers().get(0).getTotalCoin();
        var balanceOfBank = game.getBank().getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 1);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 1);
    }
}
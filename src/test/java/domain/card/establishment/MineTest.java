package domain.card.establishment;

import domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

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
        game = new TestGameBuilder().setFixedDicePoint(9).setPlayers(1).build();
        var originalBalanceOfBank = game.getBank().getTotalCoin();
        var player = game.getPlayers().get(0);
        var originalBalanceOfPlayer = player.getTotalCoin();

        mine.takeEffect(game);

        var balanceOfBank = game.getBank().getTotalCoin();
        var balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 5);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 5);

    }
}
package domain.card.establishment;

import domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class AppleOrchardTest {

    private AppleOrchard appleOrchard;
    private Game game;
    
    @BeforeEach
    void setUp() {
        appleOrchard = new AppleOrchard();
    }

    @Test
    void takeEffect() {
        game = TestUtils.createTestGameWithFixedDiceValue(10, 1);
        var player = game.getPlayers().get(0);

        var originalBalanceOfBank = game.getBank().getTotalCoin();
        var originalBalanceOfPlayer = player.getTotalCoin();

        appleOrchard.takeEffect(game, player);
        
        var balanceOfBank = game.getBank().getTotalCoin();
        var balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 3);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 3);
    }
}
package domain.card.establishment;

import domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

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
        game = new TestGameBuilder().setFixedDicePoint(10).setPlayers(1).build();
        var player = game.getPlayers().get(0);

        var originalBalanceOfBank = game.getBank().getTotalCoin();
        var originalBalanceOfPlayer = player.getTotalCoin();

        appleOrchard.takeEffect(game);
        
        var balanceOfBank = game.getBank().getTotalCoin();
        var balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 3);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 3);
    }
}
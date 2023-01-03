package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BakeryTest {
    private Game game;
    private final Bakery bakery = new Bakery();
    private Player player;

    @BeforeEach
    void setup() {
        game = new TestGameBuilder().setFixedDicePoint(3).setPlayers(1).build();
        player = game.getPlayers().get(0);
    }

    @Test
    void testTakeEffect() {
        int originalPlayerTotalCoin = player.getTotalCoin();
        int originalBankTotalCoin = game.getBank().getTotalCoin();
        player.addCardToHandCard(bakery);

        game.setTurnPlayer(player);
        bakery.takeEffect(game);

        assertThat(player.getTotalCoin()).isEqualTo(originalPlayerTotalCoin + 1);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankTotalCoin - 1);
    }
}

package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class CheeseFactoryTest {

    private Game game;
    private final CheeseFactory cheeseFactory = new CheeseFactory();
    private final Ranch ranch = new Ranch();
    private Player player;

    @BeforeEach
    void setUp() {
        game = new TestGameBuilder().setFixedDicePoint(7).setPlayers(1).build();
        player = game.getPlayers().get(0);
    }

    @Test
    void testTakeEffect() {
        int originalPlayerTotalCoin = player.getTotalCoin();
        int originalBankTotalCoin = game.getBank().getTotalCoin();
        player.addCardToHandCard(cheeseFactory);
        player.addCardToHandCard(ranch);

        game.setTurnPlayer(player);
        cheeseFactory.takeEffect(game);

        assertThat(player.getTotalCoin()).isEqualTo(originalPlayerTotalCoin + 3);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankTotalCoin - 3);
    }
}
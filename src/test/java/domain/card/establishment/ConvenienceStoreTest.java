package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class ConvenienceStoreTest {

    private Game game;
    private final ConvenienceStore convenienceStore = new ConvenienceStore();
    private Player player;

    @BeforeEach
    void setUp() {
        game = new TestGameBuilder().setFixedDicePoint(4).setPlayers(1).build();
        player = game.getPlayers().get(0);
    }

    @Test
    void testTakeEffect() {
        int originalPlayerTotalCoin = player.getTotalCoin();
        int originalBankTotalCoin = game.getBank().getTotalCoin();

        game.setTurnPlayer(player);
        convenienceStore.takeEffect(game);

        assertThat(player.getTotalCoin()).isEqualTo(originalPlayerTotalCoin + 3);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankTotalCoin - 3);
    }
}
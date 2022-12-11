package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class ConvenienceStoreTest {

    private Game game;
    private final ConvenienceStore convenienceStore = new ConvenienceStore();
    private Player player;

    @BeforeEach
    void setUp() {
        game = TestUtils.createTestGameWithFixedDiceValue(4, 1);
        player = game.getPlayers().get(0);
    }

    @Test
    void testTakeEffect() {
        int originalPlayerTotalCoin = player.getTotalCoin();
        int originalBankTotalCoin = game.getBank().getTotalCoin();

        game.setTurnPlayer(player);
        convenienceStore.takeEffect(game, player);

        assertThat(player.getTotalCoin()).isEqualTo(originalPlayerTotalCoin + 3);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankTotalCoin - 3);
    }
}
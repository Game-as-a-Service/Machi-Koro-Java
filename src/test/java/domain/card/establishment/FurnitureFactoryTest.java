package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FurnitureFactoryTest {

    private FurnitureFactory furnitureFactory;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        furnitureFactory = new FurnitureFactory();
        game = new TestGameBuilder().setFixedDicePoint(8).setPlayers(1).build();
        player = game.getPlayers().get(0);
    }

    @Test
    void takeEffect() {
        player.getEstablishments().addAll(List.of(new Forest(), new Mine()));
        int originalBalanceOfBank = game.getBank().getTotalCoin();
        int originalBalanceOfPlayer = player.getTotalCoin();
        player.addCardToHandCard(furnitureFactory);

        game.setTurnPlayer(player);
        furnitureFactory.takeEffect(game);

        int balanceOfBank = game.getBank().getTotalCoin();
        int balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 6);
        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 6);
    }

    @Test
    void takeEffectWithoutNatureResources() {
        int originalBalanceOfBank = game.getBank().getTotalCoin();
        int originalBalanceOfPlayer = player.getTotalCoin();
        player.addCardToHandCard(furnitureFactory);

        game.setTurnPlayer(player);
        furnitureFactory.takeEffect(game);

        int balanceOfBank = game.getBank().getTotalCoin();
        int balanceOfPlayer = player.getTotalCoin();

        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank);
        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer);
    }
}

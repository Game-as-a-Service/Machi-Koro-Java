package domain.card.establishment;

import domain.Bank;
import domain.card.establishment.Forest;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ForestTest {

    private Forest forest;
    Player player;
    Game game;

    @BeforeEach
    void setUp() {
        forest = new Forest();
        player = new Player("8hkibA9");
    }

    @Test
    void takeEffect() {
        game = new Game(new Bank(100), List.of(player), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 5;
            }
        };
        var originalBalanceOfPlayer = game.getPlayers().get(0).getTotalCoin();
        var originalBalanceOfBank = game.getBank().getTotalCoin();

        forest.takeEffect(game, player);

        var balanceOfPlayer = game.getPlayers().get(0).getTotalCoin();
        var balanceOfBank = game.getBank().getTotalCoin();

        assertThat(balanceOfPlayer).isEqualTo(originalBalanceOfPlayer + 1);
        assertThat(balanceOfBank).isEqualTo(originalBalanceOfBank - 1);
    }
}
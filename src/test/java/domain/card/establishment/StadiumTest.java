package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class StadiumTest {

    private Stadium stadium;
    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;


    @BeforeEach
    void setUp() {
        stadium = new Stadium();
        game = new TestGameBuilder().setFixedDicePoint(6).setPlayers(4).build();
        player1 = game.getPlayers().get(0);
        player2 = game.getPlayers().get(1);
        player3 = game.getPlayers().get(2);
        player4 = game.getPlayers().get(3);
    }

    @Test
    void takeEffect() {
        player1.addCardToHandCard(stadium);
        var originalBalanceOfPlayer1 = player1.getTotalCoin();
        var originalBalanceOfPlayer2 = player2.getTotalCoin();
        var originalBalanceOfPlayer3 = player3.getTotalCoin();
        var originalBalanceOfPlayer4 = player4.getTotalCoin();

        game.setTurnPlayer(player1);
        stadium.takeEffect(game);

        var balanceOfPlayer1 = player1.getTotalCoin();
        var balanceOfPlayer2 = player2.getTotalCoin();
        var balanceOfPlayer3 = player3.getTotalCoin();
        var balanceOfPlayer4 = player4.getTotalCoin();

        assertThat(balanceOfPlayer1).isEqualTo(originalBalanceOfPlayer1 + 6);
        assertThat(balanceOfPlayer2).isEqualTo(originalBalanceOfPlayer2 - 2);
        assertThat(balanceOfPlayer3).isEqualTo(originalBalanceOfPlayer3 - 2);
        assertThat(balanceOfPlayer4).isEqualTo(originalBalanceOfPlayer4 - 2);
    }

    @Test
    void takeEffectWhenPlayerDontHaveEnoughMoney() {
        player3.payCoin(2); // left 1 coin
        player4.payCoin(3); // left 0 coin

        player1.addCardToHandCard(stadium);
        var originalBalanceOfPlayer1 = player1.getTotalCoin();
        var originalBalanceOfPlayer2 = player2.getTotalCoin();
        var originalBalanceOfPlayer3 = player3.getTotalCoin();
        var originalBalanceOfPlayer4 = player4.getTotalCoin();

        game.setTurnPlayer(player1);
        stadium.takeEffect(game);

        var balanceOfPlayer1 = player1.getTotalCoin();
        var balanceOfPlayer2 = player2.getTotalCoin();
        var balanceOfPlayer3 = player3.getTotalCoin();
        var balanceOfPlayer4 = player4.getTotalCoin();

        assertThat(balanceOfPlayer1).isEqualTo(originalBalanceOfPlayer1 + 3);
        assertThat(balanceOfPlayer2).isEqualTo(originalBalanceOfPlayer2 - 2);
        assertThat(balanceOfPlayer3).isEqualTo(originalBalanceOfPlayer3 - 1);
        assertThat(balanceOfPlayer4).isEqualTo(originalBalanceOfPlayer4);


    }
}
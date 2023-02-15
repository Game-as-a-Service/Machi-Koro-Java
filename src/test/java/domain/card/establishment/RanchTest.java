package domain.card.establishment;

import domain.Bank;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RanchTest {
    @Test
    void testTakeEffect() {
        // given
        Game game = new Game(new Bank(100), null, null, null) {
            @Override
            public Integer getCurrentDicePoint() {
                return 2;
            }
        };
        Player playerA = new Player("A");
        Ranch ranch = new Ranch();
        playerA.addCardToHandCard(ranch);

        // when
        ranch.takeEffect(game);

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(99);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }
}

package domain.card.establishment;

import domain.Bank;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class RanchTest {
    @Test
    void testTakeEffect() {
        // given
        Player playerA = new Player("A");
        Game game = new Game(
                new Bank(100),
                Collections.singletonList(playerA),
                null,
                null) {
            @Override
            public int getCurrentDicePoint() {
                return 2;
            }
        };

        Ranch ranch = new Ranch();
        playerA.addCardToHandCard(ranch);

        // when
        ranch.takeEffect(game);

        // then
        //初始金額 = 3 元、牧場 = 1 元
        assertThat(game.getBank().getTotalCoin()).isEqualTo(99);
        assertThat(playerA.getTotalCoin()).isEqualTo(1);
    }
}

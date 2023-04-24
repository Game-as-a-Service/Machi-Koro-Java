package domain.card.establishment;

import domain.Bank;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class WheatFieldTest {
    @Test
    void testTakeEffect() {
        // given
        Player playerA = new Player("A");
        Game game = new Game(new Bank(100), Collections.singletonList(playerA), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 1;
            }
        };

        WheatField wheatField = new WheatField();
        playerA.addCardToHandCard(wheatField);

        // when
        wheatField.takeEffect(game);

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(96);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }
}
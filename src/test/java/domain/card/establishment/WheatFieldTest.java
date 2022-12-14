package domain.card.establishment;

import domain.Bank;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WheatFieldTest {
    @Test
    void testTakeEffect() {
        // given
        Game game = new Game(new Bank(100), null, null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 1;
            }
        };
        Player playerA = new Player("A");
        WheatField wheatField = new WheatField();
        playerA.addCardToHandCard(wheatField);

        // when
        wheatField.takeEffect(game);

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(99);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }
}
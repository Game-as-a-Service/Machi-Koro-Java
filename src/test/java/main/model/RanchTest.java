package main.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RanchTest {
    @Test
    void testTakeEffect() {
        // given
        Game game = new Game(new Bank(100), null, null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 2;
            }
        };
        Player playerA = new Player("A");
        Ranch ranch = new Ranch();

        // when
        ranch.takeEffect(game, playerA);

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(99);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }
}

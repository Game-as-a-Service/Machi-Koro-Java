package domain.card.establishment;

import domain.Bank;
import domain.card.establishment.Cafe;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CafeTest {

    private Cafe cafe;
    Player playerA;
    Player playerB;
    Game game;

    @BeforeEach
    void setUp() {
        cafe = new Cafe();
        playerA = new Player("A");
        playerB = new Player("B");
    }

    @Test
    void getName() {
        assertEquals("咖啡館", cafe.getName());
    }

    @Test
    void testTakeEffect() {
        //given
        game = new Game(new Bank(100), List.of(playerA, playerB), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 3;
            }
        };
        int original_playerA_totalCoin = playerA.getTotalCoin();
        int original_playerB_totalCoin = playerB.getTotalCoin();

        //when
        game.setTurnPlayer(playerA);
        cafe.takeEffect(game, playerB);

        //then
        assertEquals(original_playerA_totalCoin - 1, playerA.getTotalCoin());
        assertEquals(original_playerB_totalCoin + 1, playerB.getTotalCoin());
    }
}
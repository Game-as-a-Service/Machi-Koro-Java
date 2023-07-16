package domain.card.establishment;

import domain.Bank;
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

    /*
    * 測試咖啡店的卡片效果
    * */
    @Test
    void testTakeEffect() {
        //given
        game = new Game(new Bank(100), List.of(playerA, playerB), null, null) {
            @Override
            public int getCurrentDicePoint() {
                return 3;
            }
        };
        playerA.gainCoin(3);
        playerB.gainCoin(3);
        int originalBalanceOfPlayerA = playerA.getTotalCoin();
        int originalBalanceOfPlayerB = playerB.getTotalCoin();
        playerB.addCardToHandCard(cafe);

        //when
        game.setTurnPlayer(playerA);
        cafe.takeEffect(game);

        //then
        assertEquals(originalBalanceOfPlayerA - Cafe.EFFECT_COIN, playerA.getTotalCoin());
        assertEquals(originalBalanceOfPlayerB + Cafe.EFFECT_COIN, playerB.getTotalCoin());
    }
}

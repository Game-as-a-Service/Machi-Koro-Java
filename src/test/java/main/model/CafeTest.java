package main.model;

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
        game = new Game(new Bank(100), List.of(playerA,playerB), null, null);
        playerA.gainCoin(1);
        playerB.addCardToHandCard(cafe);

        game.setTurnPlayer(playerA);

        game.setCurrentDicePoint(2);
        cafe.takeEffect(game);

        assertEquals(0,playerA.getTotalCoin());
        assertEquals(1,playerB.getTotalCoin());
    }
}
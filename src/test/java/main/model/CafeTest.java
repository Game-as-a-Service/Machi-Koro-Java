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
        playerB.addCardToHandCard(cafe);

        game.setTurnPlayer(playerA);

        game.setCurrentDicePoint(3);
        cafe.takeEffect(game);

        assertEquals(2,playerA.getTotalCoin());
        assertEquals(4,playerB.getTotalCoin());
    }
}
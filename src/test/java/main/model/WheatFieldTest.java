package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WheatFieldTest {
    private WheatField wheatField;

    Player playerA;
    Game game;


    @BeforeEach
    void setUp() {
        wheatField = new WheatField();
        playerA = new Player("A");
    }

    @Test
    void getName() {
        assertEquals("小麥田", wheatField.getName());
    }

    @Test
    void testTakeEffect() {
        game = new Game(new Bank(100), List.of(playerA), null, null);
        game.setCurrentDicePoint(1);
        game.setTurnPlayer(playerA);

        wheatField.takeEffect(game);

        assertEquals(4, game.getPlayers().get(0).getTotalCoin());
        assertEquals(99, game.getBank().getTotalCoin());
    }
}
package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class WheatFieldTest {

    private WheatField wheatField;

    private Player player;

    private Game game;

    @BeforeEach
    void setUp() {
        wheatField = spy(new WheatField());
        player = spy(new Player("a", 3));
        game = spy(
                new Game(new ArrayDeque<>(4) {{
                    add(player);
                }},
                new Dice())
        );

    }

    @Test
    void takeEffect() {
        var establishments = List.of(wheatField,wheatField,wheatField);
        var originalCoin = player.getTotalCoin();

        doReturn(establishments).when(player).getOwnedEstablishment();
        doReturn(true).when(wheatField).isDicePointToTakeEffect(1);
        doReturn(1).when(game).getCurrentDicePoint();

        wheatField.takeEffect(game);
        assertEquals(player.getTotalCoin(), originalCoin + establishments.size());
    }
}
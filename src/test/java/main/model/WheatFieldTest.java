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
        wheatField = new WheatField();
        player = new Player("a");
        game = new Game(new ArrayDeque<>(4) {{
            add(player);
        }}, new Dice());
    }

    @Test
    void takeEffect() {
        //given three wheatFields to player
        List<Establishment> establishments = List.of(wheatField, wheatField, wheatField);
        player.setOwnedEstablishment(establishments);
        var originalCoin = player.getTotalCoin();


        //when dicepoint is 1
        game.setCurrentDicePoint(1);

        player.getOwnedEstablishment().forEach((handcard) -> {
            if (game.getCurrentDicePoint() == handcard.getDiceRollNeededToActivateEffect()) {
                handcard.takeEffect(game);
            }
        });

        //then player's totalpoints is 6
        assertEquals(player.getTotalCoin(), originalCoin + 3);
    }
}
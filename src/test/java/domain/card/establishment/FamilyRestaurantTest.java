package domain.card.establishment;

import domain.Bank;
import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FamilyRestaurantTest {
    private FamilyRestaurant familyRestaurant;
    Player playerA;
    Player playerB;
    Game game;

    @BeforeEach
    void setUp(){
        familyRestaurant = new FamilyRestaurant();
        playerA = new Player("A");
        playerB = new Player("B");
    }

    @Test
    void getName(){assertEquals("家庭餐廳",familyRestaurant.getName()); }


    @Test
    void testTakeEffect(){
        game = new Game(new Bank(200), List.of(playerA,playerB),null,null);
        playerB.addCardToHandCard(familyRestaurant);

        game.setTurnPlayer(playerA);

        game.setCurrentDicePoint(List.of(10));
        familyRestaurant.doTakeEffect(game);
        assertEquals(1,playerA.getTotalCoin());
        assertEquals(5,playerB.getTotalCoin());
    }
}

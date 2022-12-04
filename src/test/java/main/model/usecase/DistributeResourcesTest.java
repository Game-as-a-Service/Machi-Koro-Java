package main.model.usecase;

import main.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistributeResourcesTest {
    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(0 coin)及 B 玩家，A 玩家手牌裡有小麥田 " +
            "when B 玩家擲骰子是1時，系統分配資源後 " +
            "then A 玩家從銀行獲得1元(銀行 coin = 99, A 玩家 coin = 1)")
    void testWheatField() {
        // given
        Player playerA = new Player("A");
        Establishment wheatField = new WheatField();
        playerA.addCardToHandCard(wheatField);

        Player playerB = new Player("B");

        Game game = new Game(new Bank(100), List.of(playerA, playerB), List.of(new Dice()), new Marketplace());

        // when
        int playerBDicePoint = 1;
        game.setTurnPlayer(playerA);
        game.distributeResources(playerBDicePoint);

        // then
        assertEquals(99, game.getBank().getTotalCoin());
        assertEquals(1, playerA.getTotalCoin());
    }
}

package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    @DisplayName(
            "Given(Empty)" +
             "When" +
             "系統開始初始化。" +
             "Then 系統產生 108 張卡牌、282 枚 1元錢幣、2 顆骰子。")
    public void givenEmptyWhenGameInitThenGenerateCardsAndCoinAndDices() {
        //when
        Bank bank = new Bank();
        List<Player> players = new ArrayList<>();
        List<Dice> dices = new ArrayList<>();
        Marketplace marketplace = new Marketplace();
        Game game = new Game(bank, players, dices, marketplace);

        //then
        assertEquals(108, game.getMarketplace().getCards().size());
        assertEquals(282, game.getBank().getTotalCoin());
        assertEquals(2, game.getDice().size());
    }
}
package domain.card.establishment;

import domain.Game;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestGameBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FruitAndVegetableMarketTest {

    private Game game;
    private final FruitAndVegetableMarket fruitAndVegetableMarket = new FruitAndVegetableMarket();
    private Player player;

    @BeforeEach
    void setUp() {
        game = new TestGameBuilder().setFixedDicePoint(11).setPlayers(1).build();
        player = game.getPlayers().get(0);
    }

    /*
     *
     * */
    @Test
    void testTakeEffect() {
        //小麥田 +2
        WheatField wheatField = new WheatField();
        //蘋果園 +2
        AppleOrchard appleOrchard = new AppleOrchard();
        player.addCardToHandCard(wheatField);
        player.addCardToHandCard(appleOrchard);
        int originalPlayerTotalCoin = player.getTotalCoin(); // 初始金額 +3
        int originalBankTotalCoin = game.getBank().getTotalCoin();
        player.addCardToHandCard(fruitAndVegetableMarket);

        game.setTurnPlayer(player);
        fruitAndVegetableMarket.takeEffect(game);

        // 起始手牌 小麥田 +2
        // 新增手牌 小麥田 +2
        // 新增手牌 頻果園 +2
        // 玩家起始金額 + 3
        assertThat(player.getTotalCoin()).isEqualTo(originalPlayerTotalCoin + 6);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankTotalCoin - 6);
    }
}
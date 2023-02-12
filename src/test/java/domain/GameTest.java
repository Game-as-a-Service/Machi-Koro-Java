package domain;

import domain.card.establishment.Bakery;
import domain.card.establishment.WheatField;
import domain.card.landmark.Landmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

//             Given 條件
//             需要 108 張卡牌、282 枚 1元錢幣、2 顆骰子、2 ~ 4名玩家。
//             When
//             new game()
//             Then
//    @Test
//    public void givenEmptyWhenGameInitThenGenerateCardsAndCoinAndDices() {
//        //when
//        Bank bank = new Bank();
//        List<Player> players = new ArrayList<>();
//        List<Dice> dices = new ArrayList<>();
//        Marketplace marketplace = new Marketplace();
//        Game game = new Game(bank, players, dices, marketplace);
//
//        //then
//        assertEquals(108, game.getMarketplace().getCards().size());
//        assertEquals(282, game.getBank().getTotalCoin());
//        assertEquals(2, game.getDice().size());
//    }

    @ParameterizedTest
    @DisplayName("""
            Given (pre-condition)
            1. 玩家人數已介於2 ~ 4 人
            2. Bank 會有 282 枚金幣
            When (post-condition)
            產生遊戲
            Then (result)
            每位玩家須擁有
            1. 小麥田 (Bakery) x 1
            2. 麵包店 (WheatField) x 1
            3. 起始地標建築 x 4 (landmark) 建築各一張面朝下。
            4. 一個玩家可以得到 3 元。
            5. 銀行會減少(人數 * 3 元)""")
    @ValueSource(ints = {2, 3, 4})
    public void 玩家拿到基本家當準備北漂(int playerCount) {
        //Given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player("玩家" + i));
        }
        Bank bank = new Bank();
        List<Dice> dices = new ArrayList<>();
        Marketplace marketplace = new Marketplace();
        Game game = new Game(bank, players, dices, marketplace);

        int playerInitCoin = 3;
        int bankTotalCoin = bank.getTotalCoin();
        int bankLastCoin = bankTotalCoin - (players.size() * 3);

        //When
        game.setUp();

        //Then
        for (Player player : players) {
            assertEquals(1,
                    player.getOwnedEstablishment()
                            .stream()
                            .filter(card -> card.equals(new Bakery())).count());
            assertEquals(1,
                    player.getOwnedEstablishment()
                            .stream()
                            .filter(card -> card.equals(new WheatField())).count());

            assertEquals(4, player.getOwnedLandmark().size());
            for (int i = 0; i < 4; i++) {
                assertEquals(Landmark.CardSide.BACK,
                        player.getOwnedLandmark().get(i).getCardSide());
            }
            assertEquals(playerInitCoin, player.getTotalCoin());
        }
        assertEquals(bankLastCoin, bank.getTotalCoin());
    }
}
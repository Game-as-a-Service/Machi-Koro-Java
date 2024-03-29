package domain;

import domain.card.establishment.AppleOrchard;
import domain.card.establishment.Bakery;
import domain.card.establishment.Cafe;
import domain.card.establishment.ConvenienceStore;
import domain.card.establishment.Establishment;
import domain.card.establishment.WheatField;
import domain.card.landmark.Landmark;
import domain.card.landmark.ShoppingMall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static domain.Bank.TOTAL_COINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class GameTest {

    @Test
    @DisplayName("""
            Given (pre-condition)
            1. 玩家人數已介於 2 ~ 4 人
            2. Bank 會有 282 枚金幣
            When (post-condition)
            產生遊戲
            Then (result)
            每位玩家須擁有
            1. 小麥田 (WheatField) x 1
            2. 麵包店 (Bakery) x 1
            3. 起始地標建築 x 4 (landmark) 建築各一張面朝下
            4. 銀行會給予 3 枚金幣
            """)
    public void 兩位玩家拿到初始建設與金幣() {
        //Given
        List<Player> players = new ArrayList<>();
        players.add(new Player("A"));
        players.add(new Player("B"));

        Bank bank = new Bank();
        Marketplace marketplace = new Marketplace();
        int expectedBankCoins = TOTAL_COINS - (2 * Bank.INIT_PAY_COINS);

        //When
        Game game = new Game(bank, players, marketplace);
        game.setUp();

        //Then
        players.forEach(this::assertOneBakeryAndOneWheatFieldAndFourLandmarkAndThreeCoins);
        assertEquals(expectedBankCoins, bank.getTotalCoin());
    }

    private void assertOneBakeryAndOneWheatFieldAndFourLandmarkAndThreeCoins(Player player) {
        assertEquals(1, getEstablishmentCount(player, new Bakery()));

        assertEquals(1, getEstablishmentCount(player, new WheatField()));

        List<Landmark> landmarks = player.getLandmarks();

        assertEquals(4, landmarks.size());

        landmarks.forEach(landmark -> assertFalse(landmark.isFlipped()));

        assertEquals(3, player.getTotalCoins());
    }

    private long getEstablishmentCount(Player player, Establishment establishment) {
        return player.getEstablishments()
                .stream()
                .filter(card -> card.equals(establishment)).count();
    }

    @Test
    void test1() {
        Bank bank = new Bank();
        var players = List.of(new Player("A"), new Player("B"), new Player("C"), new Player("D"));
        Game game = new Game(bank, players, new Marketplace());
        var playerA = players.get(0);
        var playerB = players.get(1);
        var playerC = players.get(2);
        var playerD = players.get(3);

        playerA.gainCoin(30);
        playerB.gainCoin(30);
        playerC.gainCoin(30);
        playerD.gainCoin(30);

        playerA.buyEstablishment(new AppleOrchard(), bank);
        playerA.buyEstablishment(new AppleOrchard(), bank);
        playerA.buyEstablishment(new Cafe(), bank);
        playerA.buyEstablishment(new Bakery(), bank);
        playerA.buyEstablishment(new Bakery(), bank);

        playerB.buyEstablishment(new Cafe(), bank);
        playerB.buyEstablishment(new Cafe(), bank);

        playerB.flipLandMark(new ShoppingMall(), bank);


        playerC.buyEstablishment(new AppleOrchard(), bank);


        // when
        playerA.setTotalCoin(30);
        playerB.setTotalCoin(30);
        playerC.setTotalCoin(30);
        game.setCurrentDicePoint(10);
        game.setTurnPlayer(playerA);
        game.takeAllPlayersEffect();

        // then
        assertThat(playerA.getTotalCoins()).isEqualTo(36);
        assertThat(playerB.getTotalCoins()).isEqualTo(30);
        assertThat(playerC.getTotalCoins()).isEqualTo(33);


    }

    @Test
    @DisplayName("""
            當玩家A有便利商店2張，並且有購物中心
            當骰子擲出點數為4時
            A可以從銀行得到8元
            """)
    void 當A有便利商店2張_並且有購物中心_可以得到8元() {
        // given
        Dice dice = Mockito.mock(Dice.class);

        HandCard handCard = new HandCard();
        handCard.addHandCard(new ConvenienceStore());
        handCard.addHandCard(new ConvenienceStore());
        handCard.flipLandMark(ShoppingMall.class);
        Player playerA = Player.builder().id("id").name("A").coins(0).handCard(handCard).build();

        Game game = Game.builder()
                .players(List.of(playerA))
                .turnPlayer(playerA)
                .currentDicePoint(4)
                .dices(List.of(dice))
                .bank(new Bank())
                .build();

        var originalBankCoins = game.getBank().getTotalCoin();

        // when
        Mockito.when(dice.throwDice()).thenReturn(4);
        game.rollDice(playerA.getId(), 1);
        int covenienceSize = handCard.getEstablishments(ConvenienceStore.class).size();

        // then
        var effectCoins = (ConvenienceStore.EFFECT_COINS + ShoppingMall.BONUS) * covenienceSize;
        assertThat(playerA.getTotalCoins()).isEqualTo(effectCoins);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankCoins - effectCoins);

    }
}

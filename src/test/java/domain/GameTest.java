package domain;

import domain.card.establishment.*;
import domain.card.landmark.Landmark;
import domain.card.landmark.ShoppingMall;
import domain.card.landmark.TrainStation;
import org.junit.jupiter.api.BeforeEach;
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
    private Bank bank;
    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

    @BeforeEach
    void setUp() {
        playerA = Player.builder().id("A01").name("A").build();
        playerB = Player.builder().id("B01").name("B").build();
        playerC = Player.builder().id("C01").name("C").build();
        playerD = Player.builder().id("D01").name("D").build();
        bank = new Bank();
    }

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
        var players = List.of(playerA, playerB, playerC, playerD);
        Game game = new Game(bank, players, new Marketplace());

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

        Landmark shoppingMall = playerA.getLandMark("購物中心");
        playerA.addCardToHandCard(new ConvenienceStore());
        playerA.addCardToHandCard(new ConvenienceStore());
        //避免玩家沒有足夠的錢建造購物中心建築物
        playerA.gainCoin(10);
        playerA.flipLandMark(shoppingMall, bank);

        Game game = Game.builder()
                .players(List.of(playerA))
                .turnPlayer(playerA)
                .currentDicePoint(4)
                .dices(List.of(dice))
                .bank(bank)
                .build();

        var originalBankCoins = game.getBank().getTotalCoin();

        // when
        Mockito.when(dice.throwDice()).thenReturn(4);
        game.rollDice(playerA.getId(), false);
        int covenienceSize = playerA.getHandCard().getEstablishments().stream()
                .mapToInt(e -> e instanceof ConvenienceStore ? 1 : 0)
                .sum();

        // then
        var effectCoins = (ConvenienceStore.EFFECT_COINS + ShoppingMall.BONUS) * covenienceSize;
        assertThat(playerA.getTotalCoins()).isEqualTo(effectCoins);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankCoins - effectCoins);

    }

    @Test
    @DisplayName("""
            當玩家B有2張麵包店，且輪到B擲骰子
            當骰子擲出點數為2時
            B可以從銀行得到2元
            """)
    void greenCardbackeryCardTest() {
        // given
        Dice dice = Mockito.mock(Dice.class);

        playerB.addCardToHandCard(new Bakery());
        playerB.addCardToHandCard(new Bakery());

        Game game = Game.builder()
                .players(List.of(playerB))
                .turnPlayer(playerB)
                .currentDicePoint(2)
                .dices(List.of(dice))
                .bank(bank)
                .build();

        var originalBankCoins = game.getBank().getTotalCoin();

        // when
        Mockito.when(dice.throwDice()).thenReturn(2);
        game.rollDice(playerB.getId(), false);

        // then
        //TODO 命名為具有領域意義的名稱，會更好理解
        int totalBakeryBonuses = playerB.getHandCard().getEstablishments().stream()
                .mapToInt(e -> e instanceof Bakery ? 1 : 0)
                .sum();
        assertThat(playerB.getTotalCoins()).isEqualTo(totalBakeryBonuses);
        assertThat(game.getBank().getTotalCoin()).isEqualTo(originalBankCoins - totalBakeryBonuses);
    }

    @Test
    @DisplayName("""
            當玩家A有2張麵包店，且輪到A擲骰子
            當玩家B有2張森林
            當玩家C有1張森林
            當A骰子擲出點數為5時,
            B可以從銀行得到2元，C可以從銀行得到1元
            """)
    void buleCardForestTest() {
        // given
        Dice dice = Mockito.mock(Dice.class);

        playerA.addCardToHandCard(new Bakery());
        playerA.addCardToHandCard(new Bakery());
        playerB.addCardToHandCard(new Forest());
        playerB.addCardToHandCard(new Forest());
        playerC.addCardToHandCard(new Forest());

        Game game = Game.builder()
                .players(List.of(playerA, playerB, playerC))
                .turnPlayer(playerA)
                .currentDicePoint(5)
                .dices(List.of(dice))
                .bank(bank)
                .build();

        var originalBankCoins = game.getBank().getTotalCoin();

        // when
        Mockito.when(dice.throwDice()).thenReturn(5);
        game.rollDice(playerA.getId(), false);

        // then
        assertThat(playerA.getTotalCoins()).isEqualTo(0);
        assertThat(playerB.getTotalCoins()).isEqualTo(2);
        assertThat(playerC.getTotalCoins()).isEqualTo(1);
    }

    @Test
    @DisplayName("""
            當玩家A有1張體育館，且輪到A擲骰子
            當玩家B有10元
            當玩家C有6元
            當A骰子擲出點數為6時,
            B跟C玩家各自要給A 2元
            """)
    void purpleStatdiumTest() {
        // given
        Dice dice = Mockito.mock(Dice.class);
        Stadium stadium = new Stadium();

        playerA.addCardToHandCard(stadium);
        playerB.gainCoin(10);
        playerC.gainCoin(6);

        Game game = Game.builder()
                .players(List.of(playerA, playerB, playerC))
                .turnPlayer(playerA)
                .currentDicePoint(6)
                .dices(List.of(dice))
                .bank(bank)
                .build();

        var originalBankCoins = game.getBank().getTotalCoin();

        // when
        Mockito.when(dice.throwDice()).thenReturn(6);
        game.rollDice(playerA.getId(), false);
        // then
        assertThat(playerA.getTotalCoins()).isEqualTo(4);
        assertThat(playerB.getTotalCoins()).isEqualTo(8);
        assertThat(playerC.getTotalCoins()).isEqualTo(4);
    }

    @Test
    @DisplayName("""
            當玩家A有2張麵包店、火車站跟10元，且輪到A擲骰子
            當玩家B有1張家庭餐廳
            當A骰子擲出點數為10時,
            A要給B 2塊
            """)
    void redCardFamilyRestaurantTest() {
        // given
        Dice dice1 = Mockito.mock(Dice.class);
        Dice dice2 = Mockito.mock(Dice.class);
        playerA.addCardToHandCard(new Bakery());
        playerA.addCardToHandCard(new Bakery());

        Landmark trainStation = playerA.getLandMark("火車站");
        //避免玩家沒有足夠的錢建造購物中心建築物
        playerA.gainCoin(10);
        playerA.flipLandMark(trainStation, bank);
        playerB.addCardToHandCard(new FamilyRestaurant());

        Game game = Game.builder()
                .players(List.of(playerA, playerB))
                .turnPlayer(playerA)
                .currentDicePoint(10)
                .dices(List.of(dice1, dice2))
                .bank(bank)//這裡不是很確定
                .build();

        // when
        Mockito.when(dice1.throwDice()).thenReturn(5);
        Mockito.when(dice2.throwDice()).thenReturn(5);
        game.rollDice(playerA.getId(), true);

        //then
        assertThat(playerA.getTotalCoins()).isEqualTo(4);//10-4-2
        assertThat(playerB.getTotalCoins()).isEqualTo(2);
    }
}

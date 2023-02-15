package usecase;

import domain.*;
import domain.card.establishment.*;
import domain.card.landmark.Landmark;
import domain.card.landmark.ShoppingMall;
import domain.card.landmark.TrainStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistributeResourcesTest {
    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;
    private Establishment wheatField;
    private Establishment bakery;
    private Establishment bakery1;
    private Establishment bakery2;
    private Establishment cafe;
    private Establishment cafe1;
    private Establishment cafe2;
    private Establishment mine1;
    private Establishment mine2;
    private Establishment furnitureFactory1;
    private Establishment furnitureFactory2;
    private Establishment convenienceStore;
    private Landmark trainstation;
    private Landmark shoppingMall;
    private Game game;

    @BeforeEach
    void setUP() {
        playerA = new Player("A");
        playerB = new Player("B");
        playerC = new Player("C");
        playerD = new Player("D");
        trainstation = new TrainStation();
        shoppingMall = new ShoppingMall();
        wheatField = new WheatField();
        cafe = new Cafe();
        game = new Game(new Bank(100), List.of(playerA, playerB, playerC, playerD), List.of(new Dice()), new Marketplace());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有小麥田 " +
                    "when A 玩家擲骰子是1時，系統分配資源後 " +
                    "then A 玩家從銀行獲得1元(銀行 coin = 99, A 玩家 coin = 4)")
    void testWheatField() {
        // given
        playerA.addCardToHandCard(wheatField);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(1, game);

        // then
        assertEquals(99, game.getBank().getTotalCoin());
        assertEquals(4, playerA.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)及 B 玩家(3 coin)，B 玩家手牌裡有一張咖啡館 " +
                    "when A 玩家擲骰子是3時，系統分配資源後 " +
                    "then B 玩家從玩家A獲得1元(B 玩家 coin = 4, A 玩家 coin = 2)")
    void playerB_has_OneCafe() {
        //given
        playerB.addCardToHandCard(cafe);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(3, game);

        // then
        assertEquals(100, game.getBank().getTotalCoin());
        assertEquals(2, playerA.getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)及 B 玩家(3 coin)，B 玩家手牌裡有三張咖啡館 " +
                    "when A 玩家擲骰子是3時，系統分配資源後 " +
                    "then B 玩家從玩家A獲得3元(B玩家 coin = 6, A 玩家 coin = 0)")
    void playerB_has_threeCafe() {
        // given
        setPlayerCardAndNumber(playerB, cafe, 3);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(3, game);

        // then
        assertEquals(100, game.getBank().getTotalCoin());
        assertEquals(0, playerA.getTotalCoin());
        assertEquals(6, playerB.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(2 coin)及 B 玩家(3 coin)，B 玩家手牌裡有三張咖啡館 " +
                    "when A 玩家擲骰子是3時，系統分配資源後 " +
                    "then B 玩家從玩家A獲得2元(B玩家 coin = 5, A 玩家 coin = 0)")
    void playerB_has_threeCafe_but_player_money_isNot_enough() {
        // given
        playerA.payCoin(1);
        setPlayerCardAndNumber(playerB, cafe, 3);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(3, game);

        // then
        assertEquals(100, game.getBank().getTotalCoin());
        assertEquals(0, playerA.getTotalCoin());
        assertEquals(5, playerB.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given A,B,C,D 玩家 (A,B,C 各3 Coin) 玩家A、B、C各擁有一間咖啡館，玩家D有3元" +
                    "when D 玩家擲骰子是3時，系統分配資源後 " +
                    "then A、B、C玩家從玩家D各獲得1元，玩家D少3元 (A,B,C玩家 coin = 4,D 玩家 coin = 0)")
    void playerABC_has_OneCafe() {
        // given
        playerA.addCardToHandCard(new Cafe());
        playerB.addCardToHandCard(new Cafe());
        playerC.addCardToHandCard(new Cafe());

        // when
        game.setTurnPlayer(playerD);
        setDicePointAndTakeEffect(3, game);

        // then
        assertEquals(100, game.getBank().getTotalCoin());
        assertEquals(4, playerA.getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
        assertEquals(4, playerC.getTotalCoin());
        assertEquals(0, playerD.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given A,B,C,D 玩家 (A,B,C 各3 Coin) 玩家B、C各擁有一間咖啡館，玩家D有4元" +
                    "when D 玩家擲骰子是3時，系統分配資源後 " +
                    "then B、C玩家從玩家D各獲得1元，玩家D少2元 (A玩家 coin = 3 ,B,C玩家 coin = 4, D 玩家 coin = 0)")
    void playerBC_has_OneCafe() {
        // given
        playerD.gainCoin(1);
        playerB.addCardToHandCard(new Cafe());
        playerC.addCardToHandCard(new Cafe());

        // when
        game.setTurnPlayer(playerD);
        setDicePointAndTakeEffect(3, game);

        // then
        assertEquals(100, game.getBank().getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
        assertEquals(4, playerC.getTotalCoin());
        assertEquals(2, playerD.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有牧場 " +
                    "when 任何玩家擲骰子是2時，系統分配資源後 " +
                    "then A 玩家從銀行獲得1元(銀行 coin = 99, A 玩家 coin = 4)")
    void testRanch() {
        // given
        playerA.addCardToHandCard(new Ranch());

        // when
        game.setTurnPlayer(playerB);
        game.distributeResources(List.of(2));

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(99);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有便利商店 " +
                    "when A 玩家擲骰子是4時，系統分配資源後 " +
                    "then A 玩家從銀行獲得3元(銀行 coin = 97, A 玩家 coin = 6)")
    void testConvenienceStore() {
        // given
        playerA.addCardToHandCard(new ConvenienceStore());

        // when
        game.setTurnPlayer(playerA);
        game.distributeResources(List.of(4));

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(97);
        assertThat(playerA.getTotalCoin()).isEqualTo(6);
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有便利商店 " +
                    "when A 玩家擲骰子是1時，系統分配資源後 " +
                    "then 無發生任何影響(銀行: 100 coin、A 玩家: 3 coin)")
    void test() {
        // given
        playerA.addCardToHandCard(new ConvenienceStore());
        int originalBankTotalCoin = game.getBank().getTotalCoin();
        int originalPlayerTotalCoin = playerA.getTotalCoin();

        // when
        game.setTurnPlayer(playerA);
        game.distributeResources(List.of(1));

        // then
        assertThat(originalBankTotalCoin).isEqualTo(game.getBank().getTotalCoin());
        assertThat(originalPlayerTotalCoin).isEqualTo(playerA.getTotalCoin());
    }

    @Test
    @DisplayName("given A玩家(有麵包店、咖啡館、購物中心，餘額15coins)、B玩家(有麵包店、咖啡館 餘額3coins)" +
            "when 該回合輪到B玩家且B玩家骰到3" +
            "then A玩家除了咖啡館獲得的收入外，還可以再多得到一塊錢")
    void playerA_has_shoppingMall_setTurnplayerB_red() {
        bakery1 = new Bakery();
        bakery2 = new Bakery();
        cafe1 = new Cafe();
        cafe2 = new Cafe();
        //--A玩家目前有 green麵包店23、red咖啡館3、購物中心
        playerA.addCardToHandCard(bakery1);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerA.addCardToHandCard(cafe1);//如果別人骰出這個數字，他必須給你1元。
        playerA.gainCoin(12);//A目前餘額:15
        playerA.flipLandMark(playerA.getOwnedLandmark().get(1));//A目前餘額:5 //你的每間(紅色卡牌)和(綠色卡牌)類型的建築獲得收入時,都可以額外再多得到1元。
        //--B玩家目前有 麵包店、咖啡館
        playerB.addCardToHandCard(bakery2);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerB.addCardToHandCard(cafe2);//如果別人骰出這個數字，他必須給你1元。

        //when 輪到B玩家且骰到3
        game.setTurnPlayer(playerB);
        game.distributeResources(List.of(3));
        assertEquals(7, playerA.getTotalCoin());//5+1(咖啡館)+1(購物中心)
        assertEquals(3, playerB.getTotalCoin());//3-1(咖啡館)+1(麵包店)
    }

    @Test
    @DisplayName("given A玩家(有麵包店、礦場、家具工廠、火車站、購物中心，餘額15coins)、B玩家(有麵包店、礦場、家具工廠 餘額3coins)" +
            "when 該回合輪到A玩家且B玩家骰到" +
            "then A玩家除了礦場獲得的收入外，還可以再多得到一塊錢")
    void playerA_has_shoppingMall_setTurnplayerB_green() {
        bakery1 = new Bakery();
        bakery2 = new Bakery();
        mine1 = new Mine();
        mine2 = new Mine();
        furnitureFactory1 = new FurnitureFactory();
        furnitureFactory2 = new FurnitureFactory();
        game = new Game(new Bank(100), List.of(playerA, playerB), null, null);
        //--A玩家目前有 green麵包店23、green家具工廠8、blue礦場9、購物中心、火車站
        playerA.addCardToHandCard(bakery1);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerA.addCardToHandCard(mine1);// 任何人骰出這個數字時，你都可以從銀行獲得5元。[CardType.NATURE_RESOURCES]
        playerA.addCardToHandCard(furnitureFactory1);// 當你自己骰出這個數字時，每擁有一張[CardType.NATURE_RESOURCES]符號的建築，就可以從銀行獲得3元。
        playerA.gainCoin(12);//A目前餘額:15
        playerA.flipLandMark(trainstation);//可以有兩顆骰子
        playerA.flipLandMark(shoppingMall);//A目前餘額:15-14=1//你的每間(紅色卡牌)和(綠色卡牌)類型的建築獲得收入時,都可以額外再多得到1元。
        //--B玩家目前有 green麵包店23、green家具工廠8、blue礦場9
        playerB.addCardToHandCard(bakery2);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerB.addCardToHandCard(mine2);/// 任何人骰出這個數字時，你都可以從銀行獲得5元。[CardType.NATURE_RESOURCES]
        playerB.addCardToHandCard(furnitureFactory2);// 當你自己骰出這個數字時，每擁有一張[CardType.NATURE_RESOURCES]符號的建築，就可以從銀行獲得3元。

        //when 輪到A玩家且骰到8
        game.setTurnPlayer(playerA);
        game.distributeResources(List.of(5,3));
        assertEquals(5,playerA.getTotalCoin());//1(init)+3(家具工廠)+1(購物中心)
        assertEquals(3,playerB.getTotalCoin());//3
    }
    private void setDicePointAndTakeEffect(int point, Game game) {
        game.setCurrentDicePoint(List.of(point));
        game.distributeResources(List.of(point));
    }

    private void setPlayerCardAndNumber(Player player, Establishment establishment, int times) {
        for (int i = 0; i < times; i++) {
            player.addCardToHandCard(establishment);
        }
    }
}

package usecase;

import domain.*;
import domain.card.establishment.*;
import domain.card.landmark.Landmark;
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
        wheatField = new WheatField();
        trainstation = new TrainStation();
        convenienceStore = new ConvenienceStore();
        bakery = new Bakery();
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
    @DisplayName("given B玩家(有小麥田、麵包店、便利商店、火車站、餘額6 coins)" +
            "when 該回合輪到B玩家且B玩家有火車站決定本回合要用一顆骰子" +
            "then 發動骰子數字對應的卡牌效果"
    )
    void test_playerB_has_TrainStation() {
        //given
        playerB.gainCoin(7);//創造B玩家餘額 6 coins 的情境(3+7-4)
        playerB.flipLandMark(trainstation);
        playerB.addCardToHandCard(wheatField);//任何人骰出這個數字時，你都可以從銀行獲得1元。
        playerB.addCardToHandCard(bakery);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerB.addCardToHandCard(convenienceStore);// 當你自己骰出這個數字時，可以從銀行獲得3元。
        game.setTurnPlayer(playerB);
        //when
        game.distributeResources(List.of(4));//B玩家決定用一顆骰子
        //then
        assertEquals(9, playerB.getTotalCoin());//6+3
    }

    @Test
    @DisplayName("given A玩家(有小麥田、麵包店、礦場、火車站，餘額7coins)、B玩家(有礦場 餘額4coins)" +
            "when 該回合輪到A玩家(有火車站)並決定只用兩個骰子" +
            "then 發動骰子數字對應的卡牌效果")
    void test_playerA_has_Trainstation_useTwoDice1() {
        mine1 = new Mine();
        mine2 = new Mine();
        //given
        playerA.gainCoin(8);//創造A玩家餘額 7 coins 的情境(初始3+8-4(買火車站))
        playerA.flipLandMark(trainstation);
        playerA.addCardToHandCard(wheatField);//任何人骰出這個數字時，你都可以從銀行獲得1元。
        playerA.addCardToHandCard(bakery);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerA.addCardToHandCard(mine1);//任何人骰出這個數字時，你都可以從銀行獲得5元。
        playerB.gainCoin(1);//創造B玩家餘額4coins 的情境(初始3+1)
        playerB.addCardToHandCard(mine2);
        game.setTurnPlayer(playerA);

        //when A玩家決定用兩顆骰子
        //then
        game.distributeResources(List.of(5,4));
        assertEquals(12, playerA.getTotalCoin());//7+5
        assertEquals(9, playerB.getTotalCoin());//4+5
    }


    @Test
    @DisplayName("given A玩家(有小麥田、麵包店、礦場、火車站，餘額7coins)、B玩家(有礦場 餘額4coins)" +
            "when 該回合輪到A玩家(有火車站)並決定用兩個骰子" +
            "then 發動骰子數字對應的卡牌效果")
    void test_playerA_has_Trainstation_useTwoDice2() {
        mine1 = new Mine();
        mine2 = new Mine();
        //given
        playerA.gainCoin(8);//創造A玩家餘額 7 coins 的情境(初始3+8-4(買火車站))
        playerA.flipLandMark(trainstation);
        playerA.addCardToHandCard(wheatField);//任何人骰出這個數字時，你都可以從銀行獲得1元。
        playerA.addCardToHandCard(bakery);//當你自己骰出這個數字時，可以從銀行獲得1元。
        playerA.addCardToHandCard(mine1);//任何人骰出這個數字時，你都可以從銀行獲得5元。
        playerB.gainCoin(1);//創造B玩家餘額4coins 的情境(初始3+1)
        playerB.addCardToHandCard(mine2);
        //when
        game.setTurnPlayer(playerA);
        game.distributeResources(List.of(6, 5));//點數11 沒有發動任何對應的卡牌效果
        assertEquals(7, playerA.getTotalCoin());//7
        assertEquals(4, playerB.getTotalCoin());//4
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

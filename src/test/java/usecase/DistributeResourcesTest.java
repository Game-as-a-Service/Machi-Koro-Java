package usecase;

import domain.*;
import domain.card.establishment.*;
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
    private Establishment cafe;
    private Game game;

    @BeforeEach
    void setUP() {
        playerA = new Player("A");
        playerB = new Player("B");
        playerC = new Player("C");
        playerD = new Player("D");
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
        // 玩家A小麥田 = 2
        playerA.addCardToHandCard(wheatField);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(1, game);

        // then

        assertEquals(83, game.getBank().getTotalCoin());
        assertEquals(5, playerA.getTotalCoin());
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
        assertEquals(87, game.getBank().getTotalCoin());
        assertEquals(3, playerA.getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)及 B 玩家(3 coin)，B 玩家手牌裡有三張咖啡館 " +
                    "when A 玩家擲骰子是3時，系統分配資源後 " +
                    "then B 玩家從玩家A獲得3元(B玩家 coin = 6, A 玩家 coin = 1)")
    void playerB_has_threeCafe() {
        // given
        setPlayerCardAndNumber(playerB, cafe, 3);

        // when
        game.setTurnPlayer(playerA);
        setDicePointAndTakeEffect(3, game);

        // then
        //每位玩家初始金額 = 3 元
        assertEquals(87, game.getBank().getTotalCoin());
        assertEquals(1, playerA.getTotalCoin());
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
        //每位玩家初始金額 = 3 元
        assertEquals(87, game.getBank().getTotalCoin());
        assertEquals(0, playerA.getTotalCoin());
        assertEquals(6, playerB.getTotalCoin());
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
        //玩家初始金額 = 3 元
        assertEquals(87, game.getBank().getTotalCoin());
        assertEquals(4, playerA.getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
        assertEquals(4, playerC.getTotalCoin());
        assertEquals(1, playerD.getTotalCoin());
    }

    @Test
    @DisplayName(
            """
                    given A,B,C,D 玩家 (A,B,C 各3 Coin) 玩家B、C各擁有一間咖啡館，玩家D有4元
                    此時銀行金額為(100 - (3*3 + 4) = 87)
                    when D 玩家擲骰子是3時，系統分配資源後
                    then B、C玩家從玩家D各獲得1元，玩家D少2元
                    (A玩家 coin = 3 ,B,C玩家 coin = 4, D 玩家 coin = 2)")
                    """)
    void playerBC_has_OneCafe() {
        // given
        playerD.gainCoin(1);
        playerB.addCardToHandCard(new Cafe());
        playerC.addCardToHandCard(new Cafe());

        // when
        game.setTurnPlayer(playerD);
        setDicePointAndTakeEffect(3, game);

        // then
        // 銀行扣除 4個玩家的初始金額 12  + 咖啡館 = 1
        assertEquals(87, game.getBank().getTotalCoin());
        assertEquals(4, playerB.getTotalCoin());
        assertEquals(4, playerC.getTotalCoin());
        assertEquals(3, playerD.getTotalCoin());
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
        game.distributeResources(2);

        // then
        assertThat(game.getBank().getTotalCoin()).isEqualTo(86);
        assertThat(playerA.getTotalCoin()).isEqualTo(4);
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有便利商店 " +
                    "when A 玩家擲骰子是4時，系統分配資源後 " +
                    "then A 玩家從銀行獲得3元(銀行 coin = 86, A 玩家 coin = 6)")
    void testConvenienceStore() {
        // given
        playerA.addCardToHandCard(new ConvenienceStore());

        // when
        game.setTurnPlayer(playerA);
        game.distributeResources(4);

        // then
        //每位玩家初始金額 = 3 元
        assertThat(game.getBank().getTotalCoin()).isEqualTo(85);
        assertThat(playerA.getTotalCoin()).isEqualTo(6);
    }

    @Test
    @DisplayName(
            "given 有銀行(100 coin)、A 玩家(3 coin)，A 玩家手牌裡有便利商店 " +
                    "when A 玩家擲骰子是1時，系統分配資源後 " +
                    "then 無發生任何影響(銀行: 84 coin、A 玩家: 3 coin)")
    void test() {
        // given
        playerA.addCardToHandCard(new ConvenienceStore());
        int originalBankTotalCoin = game.getBank().getTotalCoin();
        int originalPlayerTotalCoin = playerA.getTotalCoin();

        // when
        game.setTurnPlayer(playerA);
        game.distributeResources(1);

        // then
        assertEquals(originalBankTotalCoin - game.getPlayers().size(), game.getBank().getTotalCoin());
        //初始小麥田效果 coin +1
        assertEquals(originalPlayerTotalCoin + 1, playerA.getTotalCoin());
    }

    private void setDicePointAndTakeEffect(int point, Game game) {
        game.setCurrentDicePoint(point);
        game.distributeResources(game.getCurrentDicePoint());
    }

    private void setPlayerCardAndNumber(Player player, Establishment establishment, int times) {
        for (int i = 0; i < times; i++) {
            player.addCardToHandCard(establishment);
        }
    }

    ///玩家擁有咖啡館、小麥田、麵包店
    ///當玩家骰出 3 點
    ///重新依照產業顏色(red->blue->green->purple)排序
    @Test
    public void givenPlayerHasCafeAndBreadWhenRollThreePointThenTakeEffectOrderByCustomPriority() {
        Cafe cafe = new Cafe();
        playerA.addCardToHandCard(cafe);

        game.setTurnPlayer(playerA);
        game.setCurrentDicePoint(3);
        game.distributeResources(game.getCurrentDicePoint());

        assertEquals(playerA.getHandCard(0), cafe);
        assertEquals(playerA.getHandCard(1), new WheatField());
        assertEquals(playerA.getHandCard(2), new Bakery());
    }

}

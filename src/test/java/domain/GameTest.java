package domain;

import domain.card.establishment.AppleOrchard;
import domain.card.establishment.Bakery;
import domain.card.establishment.Cafe;
import domain.card.establishment.Establishment;
import domain.card.establishment.WheatField;
import domain.card.landmark.Landmark;
import domain.card.landmark.ShoppingMall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


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

        Bank bank = new Bank(282);
        List<Dice> dices = new ArrayList<>();
        Marketplace marketplace = new Marketplace();
        int expectedBankCoins = 282 - (2 * Bank.INIT_PAY_COINS);

        //When
        Game game = new Game(bank, players, dices, marketplace);
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

        landmarks.forEach(landmark -> assertEquals(false, landmark.isFlipped()));

        assertEquals(3, player.getTotalCoin());
    }

    private long getEstablishmentCount(Player player, Establishment establishment) {
        return player.getEstablishments()
                .stream()
                .filter(card -> card.equals(establishment)).count();
    }

    @Test
    void test1() {
        var players = List.of(new Player("A"), new Player("B"), new Player("C"), new Player("D"));
        Game game = new Game(new Bank(), players, List.of(new Dice(), new Dice()), new Marketplace());
        var playerA = players.get(0);
        var playerB = players.get(1);
        var playerC = players.get(2);
        var playerD = players.get(3);

        playerA.gainCoin(30);
        playerB.gainCoin(30);
        playerC.gainCoin(30);
        playerD.gainCoin(30);

        playerA.buyCard(new AppleOrchard());
        playerA.buyCard(new AppleOrchard());
        playerA.buyCard(new Cafe());
        playerA.buyCard(new Bakery());
        playerA.buyCard(new Bakery());

        playerB.buyCard(new Cafe());
        playerB.buyCard(new Cafe());

        playerB.flipLandMark(new ShoppingMall());


        playerC.buyCard(new AppleOrchard());


        // when
        playerA.setTotalCoin(30);
        playerB.setTotalCoin(30);
        playerC.setTotalCoin(30);
        game.setCurrentDicePoint(10);
        game.setTurnPlayer(playerA);
        game.forLoopTakeEffect();

        // then
        assertThat(playerA.getTotalCoin()).isEqualTo(36);
        assertThat(playerB.getTotalCoin()).isEqualTo(30);
        assertThat(playerC.getTotalCoin()).isEqualTo(33);


    }
}

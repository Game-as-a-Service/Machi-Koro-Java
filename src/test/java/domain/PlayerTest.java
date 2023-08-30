package domain;

import domain.card.establishment.BusinessCenter;
import domain.card.establishment.Establishment;
import domain.card.establishment.WheatField;
import domain.card.landmark.AmusementPark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    private Bank bank;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("m1wt9ILw");
        bank = new Bank();
        game = new Game(bank, Collections.singletonList(player), null);
        bank.payCoin(100);
        player.gainCoin(100);
    }


    @Test
    void buyCard() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoins();
        var card = new WheatField();

        //when
        player.buyCard(card);

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer - card.getConstructionCost());
        assertThat(player.getHandCard(0)).isEqualTo(card);
    }

    @Test
    void flipBackLandMark() {
        //given 玩家有背面的主題樂園
        var originalBalanceOfPlayer = player.getTotalCoins();
        var amusementPark = new AmusementPark();

        //when
        player.flipLandMark(amusementPark);

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer - 16);
        assertTrue(player.getLandmark(2).isFlipped());
    }

    @Test
    void flipFrontLandMark() {
        //given 玩家有正面的主題樂園
        var originalBalanceOfPlayer = player.getTotalCoins();
        var amusementPark = new AmusementPark();
        player.getLandmark(2).flipped();

        //when
        NoSuchElementException actualException = Assertions.assertThrows(NoSuchElementException.class,
                () -> player.flipLandMark(amusementPark));

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer);
        assertThat(actualException.getMessage()).isEqualTo("This LandMark has been flipped");
    }


    @Test
    void givenPlayerNoHaveBusinessCenterWhenBuyTheSameCardThenSuccess() {
        var businessCenter = new BusinessCenter();
        //given 玩家沒有商業中心建築
        List<Establishment> playerOriginalOwnedEstablishment = player.getEstablishments();

        Assertions.assertFalse(playerOriginalOwnedEstablishment.contains(businessCenter));

        //when  玩家買下商業中心
        player.buyCard(businessCenter);

        //then  購買成功
        List<Establishment> playerOwnedEstablishment = player.getEstablishments();

        assertTrue(playerOwnedEstablishment.contains(businessCenter));
    }

    /*
     * Given 玩家A有商業中心
     * When  玩家A決定蓋商業中心
     * Then  系統拒絕此動作，只能擁有一座商業中心
     */
    @Test
    void givenPlayerHaveBusinessCenterWhenBuyTheSameCardThenReject() {
        var businessCenter = new BusinessCenter();
        //given 玩家有商業中心建築
        player.addCardToHandCard(businessCenter);
        List<Establishment> playerOriginalOwnedEstablishment = player.getEstablishments();

        assertTrue(playerOriginalOwnedEstablishment.contains(businessCenter));

        //when  玩家買下商業中心
        player.buyCard(businessCenter);

        //then  購買失敗，只能擁有一座商業中心
        List<Establishment> playerOwnedEstablishment = player.getEstablishments();
        long businessCenterCount = playerOwnedEstablishment
                .stream()
                .filter(card -> card.equals(businessCenter))
                .count();
        Assertions.assertEquals(businessCenterCount, 1);
    }
}

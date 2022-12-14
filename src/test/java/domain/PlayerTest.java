package domain;

import domain.card.landmark.AmusementPark;
import domain.card.establishment.WheatField;
import domain.card.landmark.Landmark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("m1wt9ILw");
        player.gainCoin(97);
    }

    @Test
    void buyCard() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoin();
        var card = new WheatField();

        //when
        player.buyCard(card);

        //then
        assertThat(player.getTotalCoin()).isEqualTo(originalBalanceOfPlayer - 1);
        assertThat(player.getOwnedEstablishment().get(0)).isEqualTo(card);
    }

    @Test
    void flipBackLandMark() {
        //given 玩家有背面的主題樂園
        var originalBalanceOfPlayer = player.getTotalCoin();
        var amusementPark = new AmusementPark();

        //when
        player.flipLandMark(amusementPark);

        //then
        assertThat(player.getTotalCoin()).isEqualTo(originalBalanceOfPlayer - 16);
        assertThat(player.getOwnedLandmark().get(2).getCardSide()).isEqualTo(Landmark.CardSide.FRONT);
    }

    @Test
    void flipFrontLandMark() {
        //given 玩家有正面的主題樂園
        var originalBalanceOfPlayer = player.getTotalCoin();
        var amusementPark = new AmusementPark();
        player.getOwnedLandmark().get(2).setCardSide(Landmark.CardSide.FRONT);

        //when
        player.flipLandMark(amusementPark);

        //then
        assertThat(player.getTotalCoin()).isEqualTo(originalBalanceOfPlayer);
        assertThat(player.getOwnedLandmark().get(2).getCardSide()).isEqualTo(Landmark.CardSide.FRONT);
    }
}
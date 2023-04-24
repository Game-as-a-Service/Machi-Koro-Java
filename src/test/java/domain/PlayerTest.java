package domain;

import domain.card.establishment.WheatField;
import domain.card.landmark.AmusementPark;
import domain.card.landmark.Landmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Bank bank;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("m1wt9ILw");
        bank = new Bank();
        game = new Game(bank, Collections.singletonList(player), null, null);
        bank.payCoin(100);
        player.gainCoin(100);
    }


    @Test
    void buyCard() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoin();
        var card = new WheatField();

        //when
        player.buyCard(card);

        //then
        assertThat(player.getTotalCoin()).isEqualTo(originalBalanceOfPlayer - card.getConstructionCost());
        assertThat(player.getHandCard(2)).isEqualTo(card);
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
        NoSuchElementException actualException = Assertions.assertThrows(NoSuchElementException.class,
                () -> player.flipLandMark(amusementPark));

        //then
        assertThat(player.getTotalCoin()).isEqualTo(originalBalanceOfPlayer);
        assertThat(actualException.getMessage()).isEqualTo("This LandMark has been flipped");
    }
}
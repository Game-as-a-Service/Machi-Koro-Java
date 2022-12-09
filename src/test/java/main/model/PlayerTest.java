package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("m1wt9ILw") {
            @Override
            public int getTotalCoin() {
                return 1000;
            }
        };
    }

    @Test
    void buyCard() {
        var card = new WheatField();
        player.buyCard(card);
        assertThat(player.getOwnedEstablishment().get(0)).isEqualTo(card);

        var landMark = new AmusementPark();
        player.buyCard(landMark);
        assertThat(player.getOwnedLandmark().get(0)).isEqualTo(landMark);

    }
}
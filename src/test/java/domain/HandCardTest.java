package domain;

import domain.card.establishment.Bakery;
import domain.card.establishment.Stadium;
import domain.card.establishment.TvStation;
import org.junit.jupiter.api.Test;

class HandCardTest {
    @Test
    void name() {
        HandCard handCard = new HandCard();
        handCard.addHandCard(new Stadium());
        handCard.addHandCard(new TvStation());
        handCard.addHandCard(new Bakery());

        handCard.removeEstablishment(1);

        System.out.println();
    }
}

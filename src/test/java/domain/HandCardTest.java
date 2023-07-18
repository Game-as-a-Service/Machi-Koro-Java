package domain;

import domain.card.establishment.Bakery;
import domain.card.establishment.Stadium;
import domain.card.establishment.TvStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandCardTest {
    @Test
    void name() {
        HandCard handCard = new HandCard();
        handCard.addCardToHandCard(new Stadium());
        handCard.addCardToHandCard(new TvStation());
        handCard.addCardToHandCard(new Bakery());

        handCard.removeEstablishment(1);

        System.out.println();
    }
}

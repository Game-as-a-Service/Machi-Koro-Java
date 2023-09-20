package domain.card.landmark;

import domain.card.CardType;

public class RadioTower extends Landmark {
    public RadioTower() {
        super("廣播電台", 22, CardType.MAJOR_ESTABLISHMENT);
    }

    public RadioTower(boolean isFlipped) {
        super("廣播電台", 22, CardType.MAJOR_ESTABLISHMENT,isFlipped);
    }
}

package domain.card.landmark;

import domain.card.CardType;

public class AmusementPark extends Landmark {
    public AmusementPark() {
        super("主題樂園", 16, CardType.MAJOR_ESTABLISHMENT);
    }

    public AmusementPark(boolean isFlipped) {
        super("主題樂園", 16, CardType.MAJOR_ESTABLISHMENT,isFlipped);
    }
}

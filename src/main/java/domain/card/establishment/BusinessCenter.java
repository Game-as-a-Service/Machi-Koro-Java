package domain.card.establishment;

import domain.card.CardType;
import domain.card.landmark.Landmark;

public class BusinessCenter extends Landmark {

    public BusinessCenter() {
        super("商業中心", 8, CardType.MAJOR_ESTABLISHMENT);
    }
}

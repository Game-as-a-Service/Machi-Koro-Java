package domain.card.establishment;

import domain.card.CardType;
import domain.card.landmark.Landmark;

public class TvStation extends Landmark {
    public TvStation() {
        super("電視台", 7, CardType.MAJOR_ESTABLISHMENT);
    }
}

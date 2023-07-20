package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {
    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, 2);
    }
}

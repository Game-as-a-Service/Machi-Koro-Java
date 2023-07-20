package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Cafe extends Establishment {
    public static final int EFFECT_COIN = 1;

    public Cafe() {
        super("咖啡館", 2, CardType.RESTAURANT, Set.of(3), IndustryColor.RED, 1);
    }
}

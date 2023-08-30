package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Mine extends Establishment {
    public static final int EFFECT_COINS = 5;

    public Mine() {
        super("礦場", 6, CardType.NATURE_RESOURCES, Set.of(9), IndustryColor.BLUE, EFFECT_COINS);
    }
}

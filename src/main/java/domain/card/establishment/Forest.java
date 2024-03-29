package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Forest extends Establishment {
    public static final int EFFECT_COINS = 1;

    public Forest() {
        super("森林", 3, CardType.NATURE_RESOURCES, Set.of(5), IndustryColor.BLUE, EFFECT_COINS);
    }
}

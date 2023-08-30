package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Bakery extends Establishment {
    public static final int EFFECT_COINS = 1;

    public Bakery() {
        super("麵包店", 1,CardType.SHOP, Set.of(2,3), IndustryColor.GREEN, EFFECT_COINS);
    }
}

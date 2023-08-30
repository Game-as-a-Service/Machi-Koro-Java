package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class AppleOrchard extends Establishment {

    public static final int EFFECT_COINS = 3;

    public AppleOrchard() {
        super("蘋果園", 3, CardType.CROP, Set.of(10), IndustryColor.BLUE, EFFECT_COINS);
    }
}

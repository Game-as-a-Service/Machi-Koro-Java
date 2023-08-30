package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class TvStation extends Establishment {
    public static final int EFFECT_COINS = 5;
    public TvStation() {
        super("電視台", 7, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, EFFECT_COINS);
    }
}

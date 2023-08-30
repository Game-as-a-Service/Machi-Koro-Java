package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class Ranch extends Establishment {
    public static final int EFFECT_COINS = 1;

    public Ranch() {
        super("牧場", 1, CardType.ANIMAL_HUSBANDRY, Set.of(2), IndustryColor.BLUE, EFFECT_COINS);
    }

}

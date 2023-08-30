package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class ConvenienceStore extends Establishment {

    public static final int EFFECT_COINS = 3;

    public ConvenienceStore() {
        super("便利商店", 1, CardType.SHOP, Set.of(4), IndustryColor.GREEN, EFFECT_COINS);
    }
}

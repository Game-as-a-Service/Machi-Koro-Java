package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class FamilyRestaurant extends Establishment {

    public static final int EFFECT_COINS = 2;

    public FamilyRestaurant() {
        super("家庭餐廳", 3, CardType.RESTAURANT, Set.of(9, 10), IndustryColor.RED, EFFECT_COINS);
    }

}

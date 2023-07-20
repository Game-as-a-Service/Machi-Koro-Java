package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class FurnitureFactory extends Establishment {

    public FurnitureFactory() {
        super("家具工廠", 3, CardType.FACTORY_OR_MARKET, Set.of(8), IndustryColor.GREEN, 3);
    }
}

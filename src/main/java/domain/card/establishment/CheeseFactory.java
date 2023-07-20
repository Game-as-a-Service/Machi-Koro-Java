package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class CheeseFactory extends Establishment {

    public CheeseFactory() {
        super("起司工廠", 5, CardType.FACTORY_OR_MARKET, Set.of(7), IndustryColor.GREEN, 3);
    }
}

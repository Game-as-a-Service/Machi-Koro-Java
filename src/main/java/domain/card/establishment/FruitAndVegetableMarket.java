package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class FruitAndVegetableMarket extends Establishment {

    public FruitAndVegetableMarket() {
        super("蔬果市場", 2, CardType.FACTORY_OR_MARKET, Set.of(11, 12), IndustryColor.GREEN, 2);
    }
}

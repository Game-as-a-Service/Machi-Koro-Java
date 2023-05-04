package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class BusinessCenter extends Establishment {

    public BusinessCenter() {
        super("商業中心", 8, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE);
    }

    @Override
    protected void doTakeEffect(Game game) {

    }
}

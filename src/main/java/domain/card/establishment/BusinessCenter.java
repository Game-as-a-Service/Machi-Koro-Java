package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class BusinessCenter extends Establishment {

    public BusinessCenter() {
        super("商業中心", 8, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, 0);
    }

    @Override
    protected void doTakeEffect(Game game) {

    }

    public boolean canTradeEstablishment(Establishment establishment) {
        return !establishment.equals(new BusinessCenter()) &&
                !establishment.equals(new Stadium()) &&
                !establishment.equals(new TvStation());
    }
}

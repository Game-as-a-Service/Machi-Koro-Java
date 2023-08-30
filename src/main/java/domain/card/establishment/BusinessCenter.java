package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class BusinessCenter extends Establishment {

    public BusinessCenter() {
        super("商業中心", 8, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, 0);
    }

    public boolean canNotTradeEstablishment(Establishment establishment) {
        return establishment.equals(new BusinessCenter()) ||
                establishment.equals(new Stadium()) ||
                establishment.equals(new TvStation());
    }
}

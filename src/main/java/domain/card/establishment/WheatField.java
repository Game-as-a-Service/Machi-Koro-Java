package domain.card.establishment;

import domain.card.CardType;

import java.util.Set;

public class WheatField extends Establishment {
    public WheatField() {
        super("小麥田", 1, CardType.CROP, Set.of(1), IndustryColor.BLUE, 1);
    }

}

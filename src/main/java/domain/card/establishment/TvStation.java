package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class TvStation extends Establishment {
    public TvStation() {
        super("電視台", 7, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, 5);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // TODO
    }
}

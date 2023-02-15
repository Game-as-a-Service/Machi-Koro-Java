package domain.card.landmark;

import domain.Game;
import domain.card.CardType;

public class RadioTower extends Landmark {
    public RadioTower() {
        super("廣播電台", 22, CardType.MAJOR_ESTABLISHMENT);
    }
    @Override
    protected void doTakeEffect(Game game) {

    }
}

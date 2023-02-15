package domain.card.landmark;

import domain.Game;
import domain.card.CardType;

public class AmusementPark extends Landmark {
    public AmusementPark() {
        super("主題樂園", 16, CardType.MAJOR_ESTABLISHMENT);
    }
    @Override
    protected void doTakeEffect(Game game) {

    }
}

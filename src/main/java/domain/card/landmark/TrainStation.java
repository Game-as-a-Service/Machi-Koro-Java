package domain.card.landmark;

import domain.card.CardType;

public class TrainStation extends Landmark {

    public TrainStation() {
        super("火車站", 4, CardType.MAJOR_ESTABLISHMENT);
    }

    public TrainStation(boolean isFlipped) {
        super("火車站", 4, CardType.MAJOR_ESTABLISHMENT);
        this.isFlipped = isFlipped;
    }
}

package domain.card.landmark;

import domain.Game;
import domain.Player;
import domain.card.CardType;

public class TrainStation extends Landmark {

    public TrainStation() {
        super("火車站", 4, CardType.MAJOR_ESTABLISHMENT);
    }

    @Override
    protected void doTakeEffect(Game game) {
        for (Player player : game.getPlayers()) {
            if (player.getOwnedLandmark().get(0).isCardsideTurnfront()) {
                game.setChooseNumberOfDice(2); // Todo 決定前端選擇骰子數量
            }
        }
    }
}

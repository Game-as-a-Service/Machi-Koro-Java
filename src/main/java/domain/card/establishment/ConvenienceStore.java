package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

public class ConvenienceStore extends Establishment {

    public ConvenienceStore() {
        super("便利商店", 1, CardType.SHOP, 4, Industry.BLUE);
    }

    public void takeEffect(Game game, Player owner) {
        // 當你自己骰出這個數字時，可以從銀行獲得3元。
        if (isDicePointToTakeEffect(game.getCurrentDicePoint()) && isTurnPlayer(game, owner)) {
            game.getBank().payCoin(3);
            owner.gainCoin(3);
        }
    }

    public boolean isTurnPlayer(Game game, Player owner) {
        return game.getTurnPlayer().equals(owner);
    }

}

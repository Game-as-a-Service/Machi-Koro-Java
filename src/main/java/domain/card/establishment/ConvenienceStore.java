package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class ConvenienceStore extends Establishment {

    public ConvenienceStore() {
        super("便利商店", 1, CardType.SHOP, Set.of(4), Industry.GREEN);
    }

    public void takeEffect(Game game) {
        // 當你自己骰出這個數字時，可以從銀行獲得3元。
        if (game.isTurnPlayer(getOwner())) {
            game.getBank().payCoin(3);
            getOwner().gainCoin(3);
        }
    }
}

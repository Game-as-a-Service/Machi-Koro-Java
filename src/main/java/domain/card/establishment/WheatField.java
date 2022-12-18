package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class WheatField extends Establishment {

    public WheatField() {
        super("小麥田", 1, CardType.CROP, Set.of(1), Industry.BLUE);
    }

    @Override
    public void doTakeEffect(Game game) {
        // 任何人骰出這個數字時，你都可以從銀行獲得1元
        game.getBank().payCoin(1);
        getOwner().gainCoin(1);
    }
}

package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class AppleOrchard extends Establishment {

    public AppleOrchard() {
        super("蘋果園", 3, CardType.CROP, Set.of(10), IndustryColor.BLUE);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // 任何人骰出這個數字時，你都可以從銀行獲得3元。
        game.getBank().payCoin(3);
        getOwner().gainCoin(3);
    }
}

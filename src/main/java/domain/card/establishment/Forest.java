package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class Forest extends Establishment {
    public Forest() {
        super("森林", 3, CardType.NATURE_RESOURCES, Set.of(5), IndustryColor.BLUE, 1);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // 任何人骰出這個數字時，你都可以從銀行獲得1元
        game.getBank().payCoin(1);
        getOwner().gainCoin(1);
    }
}

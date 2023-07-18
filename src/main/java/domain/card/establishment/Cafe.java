package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class Cafe extends Establishment {
    public static final int EFFECT_COIN = 1;

    public Cafe() {
        super("咖啡館", 2, CardType.RESTAURANT, Set.of(3), IndustryColor.RED, 1);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // 如果別人骰出這個數字，他必須給你1元
        if (!game.isTurnPlayer(getOwner())) {
            int actualRevenue = game.getTurnPlayer().checkEffectMoneyEnough(EFFECT_COIN);
            game.getTurnPlayer().payCoin(actualRevenue);
            getOwner().gainCoin(actualRevenue);
        }
    }
    private boolean playerHasEnoughCoin(Game game) {
        return game.getTurnPlayer().getTotalCoin() > 0;
    }
}

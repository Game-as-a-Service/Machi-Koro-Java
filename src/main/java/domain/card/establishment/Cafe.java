package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class Cafe extends Establishment {
    private final int COIN_TO_PAY = 1;
    private final int COIN_TO_GAIN = 1;

    public Cafe() {
        super("咖啡館", 2, CardType.RESTAURANT, Set.of(3), Industry.RED,1);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // 如果別人骰出這個數字，他必須給你1元
        if (!game.isTurnPlayer(getOwner())) {
            Integer actualRevenue = game.getTurnPlayer().checkEffectMoneyEnough(this.getEffectMoney());
            getOwner().gainCoin(actualRevenue);
        }
    }
    private boolean playerHasEnoughCoin(Game game) {
        return game.getTurnPlayer().getTotalCoin() > 0;
    }
}

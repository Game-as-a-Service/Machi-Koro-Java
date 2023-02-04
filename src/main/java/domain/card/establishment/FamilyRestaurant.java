package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class FamilyRestaurant extends Establishment{

    private final int COIN_TO_PAY = 2;
    private final int COIN_TO_GAIN = 2;
    public FamilyRestaurant() { super("家庭餐廳",3, CardType.RESTAURANT, Set.of(9,10) , Industry.RED,2); }

    @Override
    protected void doTakeEffect(Game game) {
    // 如果別人骰出這個數字，他必須給你2元
        if (!game.isTurnPlayer(getOwner())) {
            Integer actualRevenue = game.getTurnPlayer().checkEffectMoneyEnough(this.getEffectMoney());
            getOwner().gainCoin(actualRevenue);
        }
    }

    // Todo，支付足夠金幣的邏輯之後會實作在 Player 付錢方法中，這裡暫不處理先註解
}

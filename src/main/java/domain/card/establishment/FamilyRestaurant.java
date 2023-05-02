package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class FamilyRestaurant extends Establishment{

    private final int COIN_TO_PAY = 2;
    private final int COIN_TO_GAIN = 2;
    public FamilyRestaurant() { super("家庭餐廳",3, CardType.RESTAURANT, Set.of(9,10) , IndustryColor.RED); }

    @Override
    protected void doTakeEffect(Game game) {
    // 如果別人骰出這個數字，他必須給你2元
        if (!game.isTurnPlayer(getOwner())) {
            int actualRevenue = game.getTurnPlayer().checkEffectMoneyEnough(COIN_TO_PAY);
            getOwner().gainCoin(actualRevenue);
        }
    }

}

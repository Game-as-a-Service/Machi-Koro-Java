package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class FamilyRestaurant extends Establishment{

    public static final int EFFECT_COIN = 2;
    public FamilyRestaurant() { super("家庭餐廳",3, CardType.RESTAURANT, Set.of(9,10) , IndustryColor.RED); }

    @Override
    protected void doTakeEffect(Game game) {
    // 如果別人骰出這個數字，他必須給你2元
        if (!game.isTurnPlayer(getOwner())) {
            int actualRevenue = game.getTurnPlayer().checkEffectMoneyEnough(EFFECT_COIN);
            getOwner().gainCoin(actualRevenue);
        }
    }

}

package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {
    public static final int EFFECT_COIN = 2;

    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), IndustryColor.PURPLE, 2);
    }

    @Override
    protected void doTakeEffect(Game game) {
        var owner = getOwner();
        if (game.isTurnPlayer(owner)) {
            for(Player player : game.getOtherPlayers(owner)){
                int actualRevenue = player.checkEffectMoneyEnough(EFFECT_COIN);
                player.payCoin(actualRevenue);
                getOwner().gainCoin(actualRevenue);
            }
        }
    }


}

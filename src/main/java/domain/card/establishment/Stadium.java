package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {
    private final int COIN_TO_PAY = 2;
    private final int COIN_TO_GAIN = 2;

    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), Industry.PURPLE);
    }

    @Override
    protected void doTakeEffect(Game game) {
        var owner = getOwner();
        if (game.isTurnPlayer(owner)) {
            for(Player player : game.getOtherPlayers(owner)){
                int actualRevenue = player.checkEffectMoneyEnough(COIN_TO_PAY);
                getOwner().gainCoin(actualRevenue);
            }
//            var otherPlayers = game.getOtherPlayers(owner).stream().filter(player -> player.getTotalCoin() >= 0).toList();
//            var totalCoinShouldBeGain = otherPlayers.stream().reduce(
//                    0,
//                    (total, player) -> {
//                        var paidCoins = Math.min(player.getTotalCoin(), 2);
//                        player.payCoin(paidCoins);
//                        return total + paidCoins;
//                    },
//                    Integer::sum);
//
//            owner.gainCoin(totalCoinShouldBeGain);totalCoinShouldBeGain
        }
    }


}

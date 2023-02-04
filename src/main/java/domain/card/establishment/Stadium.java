package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {

    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), Industry.PURPLE,2);
    }

    @Override
    protected void doTakeEffect(Game game) {
        var owner = getOwner();
        if (game.isTurnPlayer(owner)) {
            for(Player player : game.getOtherPlayers(owner)){
                Integer actualRevenue = player.checkEffectMoneyEnough(this.getEffectMoney());
                getOwner().gainCoin(actualRevenue);
                System.out.printf("%s 給 %s $%s coins\n",player.getName(),owner.getName(),actualRevenue);
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

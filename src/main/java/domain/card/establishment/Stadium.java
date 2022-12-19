package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {

    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), Industry.PURPLE);
    }

    @Override
    protected void doTakeEffect(Game game) {
        var owner = getOwner();
        if (game.isTurnPlayer(owner)) {
            var otherPlayers = game.getPlayers()
                    .stream()
                    .filter(player -> !player.equals(owner) && player.getTotalCoin() >= 0)
                    .toList();

            var totalCoinShouldBeGain = otherPlayers.stream().reduce(
                    0,
                    (total, player) -> {
                        var paidCoins = Math.min(player.getTotalCoin(), 2);
                        player.payCoin(paidCoins);
                        return total + paidCoins;
                    },
                    Integer::sum);

            owner.gainCoin(totalCoinShouldBeGain);
        }
    }


}

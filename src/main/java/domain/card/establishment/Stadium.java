package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Stadium extends Establishment {

    public Stadium() {
        super("體育館", 6, CardType.MAJOR_ESTABLISHMENT, Set.of(6), Industry.PURPLE);
    }

    @Override
    public void takeEffect(Game game, Player owner) {
        // 當你自己骰出這個數字時，每位玩家都必須給你2元。
        if (isDicePointToTakeEffect(game.getCurrentDicePoint()) && isTurnPlayer(game, owner)) {
            var otherPlayers = game.getPlayers()
                    .stream()
                    .filter(player -> !player.equals(owner) && player.getTotalCoin() >= 0)
                    .toList();

            otherPlayers.forEach(player -> player.payCoin(Math.min(player.getTotalCoin(), 2)));
            owner.gainCoin(otherPlayers.size() * 2);
        }
    }

    public boolean isTurnPlayer(Game game, Player owner) {
        return game.getTurnPlayer().equals(owner);
    }
}

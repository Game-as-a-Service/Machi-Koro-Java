package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Cafe extends Establishment {
    private final int COIN_TO_PAY = 1;
    private final int COIN_TO_GAIN = 1;

    public Cafe() {
        super("咖啡館", 2, CardType.RESTAURANT, Set.of(3), Industry.RED);
    }

    @Override
    public void doTakeEffect(Game game, Player player) {
        // 如果別人骰出這個數字，他必須給你1元
        if (!game.isTurnPlayer(player) && playerHasEnoughCoin(game)) {
            game.getTurnPlayer().payCoin(COIN_TO_PAY);
            player.gainCoin(COIN_TO_GAIN);
        }
    }
    private boolean playerHasEnoughCoin(Game game) {
        return game.getTurnPlayer().getTotalCoin() > 0;
    }
}

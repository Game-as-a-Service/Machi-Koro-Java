package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

public class Mine extends Establishment {
    public Mine() {
        super("礦場", 6, CardType.Nature_Resources, 6, 9, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        // 任何人骰出這個數字時，你都可以從銀行獲得5元。
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(5);
            player.gainCoin(5);
        }
    }
}

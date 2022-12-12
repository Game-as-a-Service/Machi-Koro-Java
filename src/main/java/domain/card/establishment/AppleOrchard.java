package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

public class AppleOrchard extends Establishment {

    public AppleOrchard() {
        super("蘋果園", 3, CardType.CROP, 10, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        // 任何人骰出這個數字時，你都可以從銀行獲得3元。
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(3);
            player.gainCoin(3);
        }
    }
}
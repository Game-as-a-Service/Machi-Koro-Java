package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class Ranch extends Establishment {

    public Ranch() {
        super("牧場", 1, CardType.ANIMAL_HUSBANDRY, Set.of(2), Industry.BLUE);
    }

    public void takeEffect(Game game, Player owner) {
        // 任何人骰出這個數字時，你都可以從銀行獲得1元
        game.getBank().payCoin(1);
        owner.gainCoin(1);
    }
}

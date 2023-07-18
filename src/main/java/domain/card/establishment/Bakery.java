package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class Bakery extends Establishment{
    public Bakery() {
        super("麵包店", 1,CardType.SHOP, Set.of(2,3), IndustryColor.GREEN, 1);
    }

    @Override
    protected void doTakeEffect(Game game) {
        //當你自己骰出這個數字時，可以從銀行獲得1元。
        if(game.isTurnPlayer(getOwner())){
            game.getBank().payCoin(1);
            getOwner().gainCoin(1);
        }
    }
}

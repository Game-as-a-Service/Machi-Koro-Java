package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class FruitAndVegetableMarket extends Establishment {

    public FruitAndVegetableMarket() {
        super("蔬果市場", 2, CardType.FACTORY_OR_MARKET, Set.of(11, 12), Industry.GREEN);
    }

    public void doTakeEffect(Game game, Player owner) {
        // 當你自己骰出這個數字時，每擁有一張[CardType.CROP]的建築，就可以從銀行獲得2元。
        if (isTurnPlayer(game, owner)) {
            var cropCardCount = (int) owner.getOwnedEstablishment().stream()
                    .filter(establishment -> establishment.getCardType().equals(CardType.CROP))
                    .count();
            game.getBank().payCoin(cropCardCount * 2);
            owner.gainCoin(cropCardCount * 2);
        }
    }

    public boolean isTurnPlayer(Game game, Player owner) {
        return game.getTurnPlayer().equals(owner);
    }

}

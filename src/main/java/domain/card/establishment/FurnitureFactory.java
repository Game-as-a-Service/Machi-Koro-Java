package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

public class FurnitureFactory extends Establishment {

    public FurnitureFactory() {
        super("家具工廠", 3, CardType.FACTORY_OR_MARKET, Set.of(8), IndustryColor.GREEN);
    }

    @Override
    protected void doTakeEffect(Game game) {
        // 當你自己骰出這個數字時，每擁有一張[CardType.NATURE_RESOURCES]符號的建築，就可以從銀行獲得3元。
        if (game.isTurnPlayer(getOwner())) {
            var natureResourcesCardCount =
                    (int) getOwner().getOwnedEstablishment()
                            .stream()
                            .filter(card -> card.getCardType().equals(CardType.NATURE_RESOURCES))
                            .count();

            game.getBank().payCoin(natureResourcesCardCount * 3);
            getOwner().gainCoin(natureResourcesCardCount * 3);
        }
    }
}

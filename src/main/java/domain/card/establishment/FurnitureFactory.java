package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;

public class FurnitureFactory extends Establishment {

    public FurnitureFactory() {
        super("家具工廠", 3, CardType.FACTORY_OR_MARKET, Set.of(8), Industry.GREEN);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        // 當你自己骰出這個數字時，每擁有一張[CardType.NATURE_RESOURCES]符號的建築，就可以從銀行獲得3元。
        if (isTurnPlayer(game, player)) {
            var natureResourcesCardCount =
                    (int) player.getOwnedEstablishment()
                            .stream()
                            .filter(card -> card.getCardType().equals(CardType.NATURE_RESOURCES))
                            .count();

            game.getBank().payCoin(natureResourcesCardCount * 3);
            player.gainCoin(natureResourcesCardCount * 3);
        }
    }

    public boolean isTurnPlayer(Game game, Player owner) {
        return game.getTurnPlayer().equals(owner);
    }
}

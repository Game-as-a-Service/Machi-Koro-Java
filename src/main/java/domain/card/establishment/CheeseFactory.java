package domain.card.establishment;

import domain.Game;
import domain.card.CardType;

import java.util.Set;

import static domain.card.CardType.ANIMAL_HUSBANDRY;

public class CheeseFactory extends Establishment {

    public CheeseFactory() {
        super("起司工廠", 5, CardType.SHOP, Set.of(7), Industry.GREEN);
    }

    protected void doTakeEffect(Game game) {
        // 當你自己骰出這個數字時，每擁有一張[CardType.ANIMAL_HUSBANDRY]符號的建築，就可以從銀行獲得3元。
        if (game.isTurnPlayer(getOwner())) {
            int animalHusbandryCardsCount =
                    (int) getOwner().getOwnedEstablishment().stream().filter(establishment -> establishment.getCardType().equals(ANIMAL_HUSBANDRY)).count();
            //如果玩家自己沒有擁有牧場卡牌，就得不到任何收入?
            if (animalHusbandryCardsCount > 0) {
                game.getBank().payCoin(animalHusbandryCardsCount * 3);
                getOwner().gainCoin(animalHusbandryCardsCount * 3);
            }
        }
    }
}

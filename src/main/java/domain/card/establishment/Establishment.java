package domain.card.establishment;

import domain.Game;
import domain.card.Card;
import domain.card.CardType;

import java.util.Set;

public abstract class Establishment extends Card {
    private final Set<Integer> diceRollNeededToActivateEffect;
    private final Industry industry;

    public Establishment(String name, int constructionCost, CardType cardType, Set<Integer> diceRollNeededToActivateEffect, Industry industry) {
        super(name, constructionCost, cardType);
        this.diceRollNeededToActivateEffect = diceRollNeededToActivateEffect;
        this.industry = industry;
    }

    @Override
    public void takeEffect(Game game) {
        isDicePointToTakeEffect(game);
    }

    protected abstract void doTakeEffect(Game game);

    public Set<Integer> getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }


    protected void isDicePointToTakeEffect(Game game) {
        Boolean isTakeEffect = getDiceRollNeededToActivateEffect().contains(game.getCurrentDicePoint());
        if (isTakeEffect) {
            doTakeEffect(game);
        }
    }


    public Industry getIndustry() {
        return industry;
    }


}

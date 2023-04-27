package domain.card.establishment;

import domain.Game;
import domain.card.Card;
import domain.card.CardType;

import java.util.Set;

public abstract class Establishment extends Card {
    private final Set<Integer> diceRollNeededToActivateEffect;
    private final IndustryColor industryColor;

    public Establishment(String name, int constructionCost, CardType cardType, Set<Integer> diceRollNeededToActivateEffect, IndustryColor industryColor) {
        super(name, constructionCost, cardType);
        this.diceRollNeededToActivateEffect = diceRollNeededToActivateEffect;
        this.industryColor = industryColor;
    }

    @Override
    public void takeEffect(Game game) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            doTakeEffect(game);
        }
    }

    protected abstract void doTakeEffect(Game game);

    public Set<Integer> getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }


    protected boolean isDicePointToTakeEffect(int dicePoint) {
        return this.getDiceRollNeededToActivateEffect().contains(dicePoint);
    }

    public IndustryColor getIndustryColor() {
        return this.industryColor;
    }
}

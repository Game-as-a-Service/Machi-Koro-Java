package domain.card.establishment;

import domain.Game;
import domain.Player;
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

    public Set<Integer> getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }


    public void doTakeEffect(Game game) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            takeEffect(game);
        }
    }

    @Override
    public abstract void takeEffect(Game game);

    protected boolean isDicePointToTakeEffect(int dicePoint) {
        return this.getDiceRollNeededToActivateEffect().contains(dicePoint);
    }


}

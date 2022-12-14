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

    @Override
    public void takeEffect(Game game, Player player) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            doTakeEffect(game, player);
        }
    }

    public abstract void doTakeEffect(Game game, Player player);

    public Set<Integer> getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }


    protected boolean isDicePointToTakeEffect(int dicePoint) {
        return this.getDiceRollNeededToActivateEffect().contains(dicePoint);
    }


}

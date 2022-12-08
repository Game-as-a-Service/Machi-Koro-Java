package main.model;

public abstract class Establishment extends Card {
    private final int diceRollNeededToActivateEffect;
    private final Industry industry;

    public Establishment(String name,int constructionCost, CardType cardType, int quantity, int diceRollNeededToActivateEffect, Industry industry) {
        super(name, constructionCost, cardType, quantity);
        this.diceRollNeededToActivateEffect = diceRollNeededToActivateEffect;
        this.industry = industry;
    }

    public int getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }

    @Override
    public abstract void takeEffect(Game game,Player player);

    protected boolean isDicePointToTakeEffect(int dicePoint) {
        return dicePoint == this.getDiceRollNeededToActivateEffect();
    }
}

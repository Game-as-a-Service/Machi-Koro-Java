package main.model;

public abstract class Establishment extends Card {
    private final int diceRollNeededToActivateEffect;
    private final Industry industry;

    public Establishment(
            String name,
            int constructionCost,
            CardType cardType,
            int quantity,
            int diceRollNeededToActivateEffect,
            Industry industry
    ) {
        super(name, constructionCost, cardType, quantity);
        this.diceRollNeededToActivateEffect = diceRollNeededToActivateEffect;
        this.industry = industry;
    }

    public int getDiceRollNeededToActivateEffect() {
        return diceRollNeededToActivateEffect;
    }

    @Override
    public abstract void takeEffect(Game game);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establishment that)) return false;
        if (!super.equals(o)) return false;

        if (diceRollNeededToActivateEffect != that.diceRollNeededToActivateEffect) return false;
        return industry == that.industry;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + diceRollNeededToActivateEffect;
        result = 31 * result + industry.hashCode();
        return result;
    }
}

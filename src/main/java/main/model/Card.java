package main.model;

public abstract class Card {

    private final String name;
    private final int constructionCost;
    private final CardType cardType;
    private final int quantity;

    public Card(String name, int constructionCost, CardType cardType, int quantity) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.cardType = cardType;
        this.quantity = quantity;
    }

    public abstract void takeEffect(Game game);


    public String getName() {
        return name;
    }

    public int getConstructionCost() {
        return constructionCost;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;

        if (constructionCost != card.constructionCost) return false;
        if (!name.equals(card.name)) return false;
        return cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + constructionCost;
        result = 31 * result + cardType.hashCode();
        return result;
    }
}

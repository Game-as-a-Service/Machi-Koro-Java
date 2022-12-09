package main.model;

import java.util.Objects;

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

    public abstract void takeEffect(Game game, Player player);


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
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

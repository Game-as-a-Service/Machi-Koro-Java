package main.model;

public abstract class Card {

    private String name;

    private int constructionCost;

    private CardType cardType;

    private int quantity;

    public abstract void takeEffect();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConstructionCost() {
        return constructionCost;
    }

    public void setConstructionCost(int constructionCost) {
        this.constructionCost = constructionCost;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

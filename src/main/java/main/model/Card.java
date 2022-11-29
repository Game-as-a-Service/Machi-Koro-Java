package main.model;

public abstract class Card {

    private final String name;
    private Player player;
    private final int constructionCost;
    private final CardType cardType;
    private final int quantity;

    public Card(String name, Player player, int constructionCost, CardType cardType, int quantity) {
        this.name = name;
        this.player = player;
        this.constructionCost = constructionCost;
        this.cardType = cardType;
        this.quantity = quantity;
    }

    public abstract void takeEffect(Game game);


    public String getName() {
        return name;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
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


}

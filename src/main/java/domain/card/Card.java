package domain.card;

import domain.Game;
import domain.Player;
import java.util.Objects;

public abstract class Card {

    private final String name;
    private final int constructionCost;
    private final CardType cardType;

    private Player owner;

    public Card(String name, int constructionCost, CardType cardType) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.cardType = cardType;
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

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
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

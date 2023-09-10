package domain.card;

import domain.Game;
import domain.Player;
import java.util.Objects;

public abstract class Card {
    protected final String name;
    protected final int constructionCost;
    protected final CardType cardType;

    public Card(String name, int constructionCost, CardType cardType) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public int getConstructionCost() {
        return constructionCost;
    }

    public CardType getCardType() {
        return cardType;
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

package domain.card.landmark;

import domain.card.Card;
import domain.card.CardType;

public class Landmark extends Card {
    private boolean flipped;

    public Landmark(String name, int constructionCost, CardType cardType) {
        super(name, constructionCost, cardType);
    }

    public enum CardSide {
        FRONT, BACK
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

}

package domain.card.landmark;

import domain.card.Card;
import domain.card.CardType;

public class Landmark extends Card {
    private boolean isFlipped = false;

    public Landmark(String name, int constructionCost, CardType cardType) {
        super(name, constructionCost, cardType);
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flipped() {
        isFlipped = true;
    }

}

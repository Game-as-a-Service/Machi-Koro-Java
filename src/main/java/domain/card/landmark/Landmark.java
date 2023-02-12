package domain.card.landmark;

import domain.Game;
import domain.card.Card;
import domain.card.CardType;

public abstract class Landmark extends Card {
    private CardSide cardSide = CardSide.BACK;

    public Landmark(String name, int constructionCost, CardType cardType) {
        super(name, constructionCost, cardType);
    }

    @Override
    public void takeEffect(Game game) {
        doTakeEffect(game);
    }

    protected abstract void doTakeEffect(Game game);

    public boolean isCardsideTurnfront() {
        return this.cardSide.equals(CardSide.FRONT);
    }

    public enum CardSide {
        FRONT, BACK
    }

    public CardSide getCardSide() {
        return cardSide;
    }

    public void setCardSide(CardSide cardSide) {
        this.cardSide = cardSide;
    }
}

package domain.card.landmark;

import domain.Game;
import domain.Player;
import domain.card.Card;
import domain.card.CardType;

public class Landmark extends Card {
    private CardSide cardSide = CardSide.BACK;

    public Landmark(String name, int constructionCost, CardType cardType) {
        super(name, constructionCost, cardType);
    }

    @Override

    public void takeEffect(Game game, Player player) {


    }

    public enum CardSide {
        FRONT, BACK
    }
}

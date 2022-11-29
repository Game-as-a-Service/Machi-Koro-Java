package main.model;

public class Landmark extends Card {
    private CardSide cardSide = CardSide.BACK;

    public Landmark(String name, Player player, int constructionCost, CardType cardType, int quantity) {
        super(name, player, constructionCost, cardType, quantity);
    }

    @Override
    public void takeEffect(Game game) {

    }

    public enum CardSide {
        FRONT, BACK
    }
}

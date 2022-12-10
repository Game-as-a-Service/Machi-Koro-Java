package main.model;

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

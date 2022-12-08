package main.model;

public class Landmark extends Card {
    private CardSide cardSide = CardSide.BACK;

    public Landmark(String name, int constructionCost, CardType cardType, int quantity) {
        super(name, constructionCost, cardType, quantity);
    }

    @Override
    public void takeEffect(Game game) {

    }

    public enum CardSide {
        FRONT, BACK
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Landmark landmark)) return false;
        if (!super.equals(o)) return false;

        return cardSide == landmark.cardSide;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cardSide.hashCode();
        return result;
    }
}

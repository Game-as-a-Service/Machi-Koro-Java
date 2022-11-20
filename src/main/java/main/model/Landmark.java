package main.model;

public class Landmark extends Card {
    private CardSide cardSide;

    @Override
    public void takeEffect() {

    }

    public enum CardSide {
        FRONT, BACK;
    }
}

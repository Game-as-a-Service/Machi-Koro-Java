package main.model;

public abstract class Card {

    private String cardName;

    private int buildCoin;

    private CardType cardType;

    private int cardNumbers;

    public abstract void takeEffect();


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getBuildCoin() {
        return buildCoin;
    }

    public void setBuildCoin(int buildCoin) {
        this.buildCoin = buildCoin;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(int cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

}

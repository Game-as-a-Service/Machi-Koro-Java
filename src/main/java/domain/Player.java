package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.establishment.Establishment;
import domain.card.establishment.IndustryColor;
import domain.card.landmark.Landmark;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private int coins;
    private HandCard handCard = new HandCard();

    public Player(String name) {
        this.name = name;
        handCard.setPlayer(this);
    }

    public void addCardToHandCard(Establishment establishment) {
        establishment.setOwner(this);
        handCard.addCardToHandCard(establishment);
    }

    public int getTotalCoin() {
        return coins;
    }

    public List<Establishment> getEstablishments() {
        return handCard.getEstablishments();
    }

    public List<Establishment> getEstablishments(CardType cardType) {
        return handCard.getEstablishments().stream()
                .filter(establishment -> establishment.getCardType().equals(CardType.CROP))
                .collect(Collectors.toList());
    }

    public List<Landmark> getLandmarks() {
        return handCard.getLandmarks();
    }

    public Landmark getLandmark(int index) {
        return handCard.getLandmarks().get(index);
    }

    public void buyCard(Establishment card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        if (card.getIndustryColor().equals(IndustryColor.PURPLE)) {
            if (hasTheSamePurpleCard(card)) {
                //throw new RuntimeException("You already own this card!");
                return; // FIXME: 2023/04/27 throw Exception or other way to handle this condition.
            }
        }
        this.payCoin(cost);
        handCard.addCardToHandCard(card);
    }

    public void flipLandMark(Landmark card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        handCard.getLandmarks()
                .stream()
                .filter(l -> l.equals(card) && l.isFlipped() == false)
                .findFirst()
                .map(targetlandmark -> {
                    targetlandmark.setFlipped(true);
                    this.payCoin(cost);
                    return targetlandmark;
                })
                .orElseThrow(() -> new NoSuchElementException("This LandMark has been flipped"));
    }

    public int checkEffectMoneyEnough(int effectMoney) {
        int actualCoin;
        if (effectMoney > this.getTotalCoin()) {
            actualCoin = this.getTotalCoin();
            this.coins = 0;
        } else {
            actualCoin = effectMoney;
            this.payCoin(effectMoney);
        }
        return actualCoin;
    }

    public void payCoin(int coin) {
        this.coins -= coin;
    }

    public void gainCoin(int coin) {
        this.coins += coin;
    }

    public void establishmentTakeEffect(Game game) {
        handCard.getEstablishments().sort(Comparator.comparing(establishment -> establishment.getIndustryColor().getOrder()));
        handCard.getEstablishments().forEach(establishment -> establishment.takeEffect(game));
    }

    private boolean isBalanceEnough(int cost) {
        return getTotalCoin() >= cost;
    }


    public String getName() {
        return name;
    }

    public Card getHandCard(int index) {
        return handCard.getHandCard(index);
    }

    //購買紫色建築物時，判斷玩家手上是否已有相同建築物
    private boolean hasTheSamePurpleCard(Establishment toBuyCard) {
        return handCard.getEstablishments().contains(toBuyCard);
    }
}

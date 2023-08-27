package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.establishment.Establishment;
import domain.card.establishment.IndustryColor;
import domain.card.landmark.Landmark;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class Player {
    private String id;
    private final String name;
    private int coins;
    @Builder.Default
    private HandCard handCard = new HandCard();

    public Player(String name) {
        this.name = name;
        this.handCard = new HandCard();
    }

    public void addCardToHandCard(Establishment establishment) {
        handCard.addCardToHandCard(establishment);
    }

    public String getId() {
        return this.id;
    }

    public int getTotalCoin() {
        return coins;
    }

    public void setTotalCoin(int coins) {
        this.coins = coins;
    }

    public HandCard getHandCard() {
        return this.handCard;
    }

    public List<Establishment> getEstablishments() {
        return handCard.getEstablishments();
    }

    public List<Establishment> getEstablishments(int dicePoint, IndustryColor industryColor) {
        return handCard.getEstablishments().stream()
                .filter(establishment ->
                        establishment.getDiceRollNeededToActivateEffect().contains(dicePoint) &&
                                establishment.getIndustryColor().equals(industryColor)).toList();
    }

    public List<Establishment> getEstablishments(IndustryColor industryColor) {
        return handCard.getEstablishments().stream().filter(establishment -> establishment.getIndustryColor().equals(industryColor)).toList();
    }

    public List<Establishment> getEstablishments(int dicePoint) {
        return handCard.getEstablishments().stream().filter(establishment -> establishment.getDiceRollNeededToActivateEffect().contains(dicePoint)).toList();
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

        handCard.flipLandMark(card);
        this.payCoin(cost);

    }

    public int checkEffectMoneyEnough(int effectMoney) {
        return Math.min(effectMoney, this.getTotalCoin());
    }

    public void payCoin(int coin) {
        this.coins -= coin;
    }

    public void gainCoin(int coin) {
        this.coins += coin;
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

    public boolean hasLandmarkFlipped(Landmark landmark) {
        return handCard.getLandmarks().stream().anyMatch(lm -> lm.equals(landmark) && lm.isFlipped());
    }

    public void removeEstablishment(int index) {

    }

}

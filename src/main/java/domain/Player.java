package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.establishment.Establishment;
import domain.card.establishment.IndustryColor;
import domain.card.landmark.Landmark;
import domain.exceptions.MachiKoroException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Player {
    private String id;
    private final String name;
    private int coins;
    @Builder.Default
    private HandCard handCard = new HandCard();

    public Player(String id, String name) {
        this(name);
        this.id = id;
    }

    public Player(String name) {
        this.name = name;
        this.handCard = new HandCard();
    }

    public void addCardToHandCard(Establishment establishment) {
        handCard.addHandCard(establishment);
    }

    public String getId() {
        return this.id;
    }

    public int getTotalCoins() {
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

    public List<Establishment> getEstablishments(CardType cardType) {
        return handCard.getEstablishments().stream()
                .filter(establishment -> establishment.getCardType().equals(cardType))
                .collect(Collectors.toList());
    }

    public List<Landmark> getLandmarks() {
        return handCard.getLandmarks();
    }

    public Landmark getLandmark(int index) {
        return handCard.getLandmarks().get(index);
    }

    public Landmark getLandMark(String landMarkName) {
        return handCard.getLandmarks().stream().filter(landmark -> landMarkName.equals(landmark.getName())).findFirst().orElseThrow();
    }

    public void buyEstablishment(Establishment card, Bank bank) {
        int cost = card.getConstructionCost();
        checkBalanceIsEnough(cost);
        checkHasTheSamePurpleEstablishment(card);
        this.payCoin(cost);
        bank.gainCoin(cost);
        handCard.addHandCard(card);
    }

    public void flipLandMark(Landmark card, Bank bank) {
        int cost = card.getConstructionCost();
        checkBalanceIsEnough(cost);
        if (card.isFlipped()) {
            throw new MachiKoroException("此地標已經翻面，無法再重新翻面");
        }
        card.flipped();
        this.payCoin(cost);
        bank.gainCoin(cost);
    }

    private void checkBalanceIsEnough(int cost) {
        if (!isBalanceEnough(cost))
            throw new MachiKoroException("您沒有足夠的錢建造此建築物");
    }

    private void checkHasTheSamePurpleEstablishment(Establishment card) {
        if (hasTheSamePurpleException(card))
            throw new MachiKoroException("您已擁有此重要建築，不得重複建造");
    }

    //購買紫色建築物時，判斷玩家手上是否已有相同建築物
    private boolean hasTheSamePurpleException(Establishment toBuyCard) {
        return toBuyCard.getIndustryColor() == IndustryColor.PURPLE && handCard.getEstablishments().contains(toBuyCard);
    }

    public boolean hasLandmarkFlipped(Class<? extends Landmark> landmark) {
        return handCard.getLandmarks().stream().anyMatch(lm -> lm.getClass() == landmark && lm.isFlipped());
    }

    public void removeEstablishment(int index) {

    }

    public int checkEffectMoneyEnough(int effectMoney) {
        return Math.min(effectMoney, this.getTotalCoins());
    }

    public void payCoin(int coin) {
        this.coins -= coin;
    }

    public void gainCoin(int coin) {
        this.coins += coin;
    }

    private boolean isBalanceEnough(int cost) {
        return getTotalCoins() >= cost;
    }

    public String getName() {
        return name;
    }

    public Card getHandCard(int index) {
        return handCard.getHandCard(index);
    }
}

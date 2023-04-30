package domain;

import domain.card.Card;
import domain.card.establishment.Establishment;
import domain.card.establishment.IndustryColor;
import domain.card.landmark.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Player {
    private final String name;
    private int coins;
    private final List<Establishment> ownedEstablishment = new ArrayList<>();
    private final List<Landmark> ownedLandmark = Arrays.asList(new TrainStation(), new ShoppingMall(), new AmusementPark(), new RadioTower()); //FIXME: Do we need to initialize?

    public Player(String name) {
        this.name = name;
    }

    public void addCardToHandCard(Establishment establishment) {
        establishment.setOwner(this);
        this.ownedEstablishment.add(establishment);
    }

    public int getTotalCoin() {
        return coins;
    }

    public List<Establishment> getOwnedEstablishment() {
        return ownedEstablishment;
    }

    public List<Landmark> getOwnedLandmark() {
        return ownedLandmark;
    }

    public void buyCard(Establishment card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        if (card.getIndustryColor().equals(IndustryColor.PURPLE)){
            if(hasTheSamePurpleCard(card)){
                //throw new RuntimeException("You already own this card!");
                return; // FIXME: 2023/04/27 throw Exception or other way to handle this condition.
            };
        }

        this.payCoin(cost);
        addCardToHandCard(card);
    }

    public void flipLandMark(Landmark card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        Landmark landmark = getOwnedLandmark().stream()
                .filter(l -> l.equals(card) && l.getCardSide().equals(Landmark.CardSide.BACK))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("This LandMark has been flipped"));
        landmark.setCardSide(Landmark.CardSide.FRONT);
        payCoin(cost);
    }

    public void payCoin(int coin) {
        this.coins -= coin;
    }

    public void gainCoin(int coin) {
        this.coins += coin;
    }

    public void ownedEstablishmentTakeEffect(Game game) {
        ownedEstablishment.forEach(establishment -> establishment.takeEffect(game));
    }

    private boolean isBalanceEnough(int cost) {
        return getTotalCoin() >= cost;
    }

    public Card getHandCard(int index) {
        if (index < 0 || index >= ownedEstablishment.size()){
            throw new IllegalArgumentException();
        }
        return ownedEstablishment.get(index);
    }

    //購買紫色建築物時，判斷玩家手上是否已有相同建築物
    private boolean hasTheSamePurpleCard(Establishment toBuyCard){
       return this.getOwnedEstablishment().contains(toBuyCard);
    }
}

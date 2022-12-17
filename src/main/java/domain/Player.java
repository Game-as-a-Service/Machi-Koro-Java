package domain;

import domain.card.establishment.Establishment;
import domain.card.landmark.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Player {
    private final String name;
    private int totalCoin = 3;
    private List<Establishment> ownedEstablishment = new ArrayList<>();
    private List<Landmark> ownedLandmark = new ArrayList<>(Arrays.asList(new TrainStation(), new ShoppingMall(), new AmusementPark(), new RadioTower())); //FIXME: Do we need to initialize?

    public Player(String name) {
        this.name = name;
    }

    public void addCardToHandCard(Establishment establishment) {
        this.ownedEstablishment.add(establishment);
    }

    public int getTotalCoin() {
        return totalCoin;
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

        this.payCoin(cost);
        addCardToHandCard(card);
    }

    public void flipLandMark(Landmark card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        getOwnedLandmark().stream()
                .filter(landmark -> landmark.equals(card) && landmark.getCardSide().equals(Landmark.CardSide.BACK))
                .findFirst()
                .map(targetlandmark -> {
                    targetlandmark.setCardSide(Landmark.CardSide.FRONT);
                    this.payCoin(cost);
                    return targetlandmark;
                })
                .orElseThrow(()-> new NoSuchElementException("This LandMark has been flipped"));
    }

    public void payCoin(int coin) {
        this.totalCoin -= coin;
    }

    public void gainCoin(int coin) {
        this.totalCoin += coin;
    }

    public void ownedEstablishmentTakeEffect(Game game) {
        ownedEstablishment.forEach(establishment -> establishment.doTakeEffect(game, this));
    }

    private boolean isBalanceEnough(int cost) {
        return getTotalCoin() >= cost;
    }
}

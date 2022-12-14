package domain;

import domain.card.establishment.Establishment;
import domain.card.landmark.Landmark;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int totalCoin = 3;
    private List<Establishment> ownedEstablishment = new ArrayList<>();
    private List<Landmark> ownedLandmark = new ArrayList<>();

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

        totalCoin -= cost; // FIXME: 2022/12/8 need to refactor after payCoin implemented.
        getOwnedEstablishment().add(card);
    }

    public void buyCard(Landmark card) {
        int cost = card.getConstructionCost();
        if (!isBalanceEnough(cost))
            return; // FIXME: 2022/12/8 throw Exception or other way to handle this condition.

        totalCoin -= cost; // FIXME: 2022/12/8 need to refactor after payCoin implemented.
        getOwnedLandmark().add(card);
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

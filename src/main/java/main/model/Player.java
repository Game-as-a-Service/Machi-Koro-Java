package main.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int totalCoin;
    private final List<Establishment> ownedEstablishment;
    private final List<Landmark> ownedLandMark;

    public Player(String name, int coin) {
        this.name = name;
        this.totalCoin = coin;
        ownedEstablishment = new ArrayList<>();
        ownedLandMark = new ArrayList<>();
    }

    public void rollTheDice() {

    }

    public void buyCard() {

    }

    public void payCoin() {

    }

    public void gainCoin(int coin) {
        totalCoin += coin;
    }

    public int getTotalCoin() {
        return totalCoin;
    }

    public List<Establishment> getOwnedEstablishment() {
        return ownedEstablishment;
    }

    public List<Landmark> getOwnedLandMark() {
        return ownedLandMark;
    }

    public void build(Landmark card) {
        var cost = card.getConstructionCost();
        if (!isCoinBalanceEnough(cost)) return;
        // FIXME: 2022/11/30 maybe throw exception ?

        this.totalCoin -= cost;
        this.ownedLandMark.add(card);
    }

    public void build(Establishment card) {
        var cost = card.getConstructionCost();
        if (!isCoinBalanceEnough(cost)) return;
        // FIXME: 2022/11/30 maybe throw exception ?

        this.totalCoin -= cost;
        this.ownedEstablishment.add(card);
    }

    public boolean isCoinBalanceEnough(int cost) {
        return this.totalCoin >= cost;
    }

}

package main.model;

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

    public void buyCard() {

    }

    public void payCoin(int coin) {
       this.totalCoin -= coin;
    }

    public void gainCoin(int coin) {
        this.totalCoin += coin;
    }
}

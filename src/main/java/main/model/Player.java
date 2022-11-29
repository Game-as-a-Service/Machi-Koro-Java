package main.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int totalCoin = 0;
    private List<Establishment> handEstablishment = new ArrayList<>();
    private List<Landmark> handLandmark = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCardToHandCard(Establishment establishment) {
        this.handEstablishment.add(establishment);
        establishment.setPlayer(this);
    }

    public int getTotalCoin() {
        return totalCoin;
    }

    public List<Establishment> getHandEstablishment() {
        return handEstablishment;
    }

    public void buyCard() {

    }

    public void payCoin() {

    }

    public void gainCoin(int coin) {
        this.totalCoin += coin;

    }


}

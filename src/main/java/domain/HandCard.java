package domain;

import domain.card.Card;
import domain.card.establishment.Establishment;
import domain.card.landmark.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandCard {
    private Player player;
    private final List<Establishment> ownedEstablishment = new ArrayList<>();
    private final List<Landmark> ownedLandmark = Arrays.asList(new TrainStation(), new ShoppingMall(), new AmusementPark(), new RadioTower());

    public void addCardToHandCard(Establishment establishment) {
        ownedEstablishment.add(establishment);
    }

    public Card getHandCard(int index){
        if (index < 0 || index >= getOwnedLandmark().size()) {
            throw new IllegalArgumentException();
        }
        return ownedEstablishment.get(index);
    }
    public List<Establishment> getOwnedEstablishment() {
        return ownedEstablishment;
    }
    public List<Landmark> getOwnedLandmark() {
        return ownedLandmark;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}

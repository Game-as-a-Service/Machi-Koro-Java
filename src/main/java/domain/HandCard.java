package domain;

import domain.card.Card;
import domain.card.establishment.Establishment;
import domain.card.landmark.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandCard {
    private Player player;
    private final List<Establishment> establishments = new ArrayList<>();
    private final List<Landmark> landmarks = Arrays.asList(new TrainStation(), new ShoppingMall(), new AmusementPark(), new RadioTower());

    public void addCardToHandCard(Establishment establishment) {
        establishments.add(establishment);
    }

    public Card getHandCard(int index){
        if (index < 0 || index >= getLandmarks().size()) {
            throw new IllegalArgumentException();
        }
        return establishments.get(index);
    }
    public List<Establishment> getEstablishments() {
        return establishments;
    }
    public List<Landmark> getLandmarks() {
        return landmarks;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}

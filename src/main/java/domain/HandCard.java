package domain;

import domain.card.Card;
import domain.card.establishment.Establishment;
import domain.card.landmark.AmusementPark;
import domain.card.landmark.Landmark;
import domain.card.landmark.RadioTower;
import domain.card.landmark.ShoppingMall;
import domain.card.landmark.TrainStation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class HandCard {

    private List<Establishment> establishments = new ArrayList<>();
    private List<Landmark> landmarks = Arrays.asList(new TrainStation(), new ShoppingMall(), new AmusementPark(), new RadioTower());

    public HandCard() {
    }

    public HandCard(List<Establishment> establishments, List<Landmark> landmarks) {
        this.establishments = establishments;
        this.landmarks = landmarks;
    }

    public void addCardToHandCard(Establishment establishment) {
        establishments.add(establishment);
    }

    public void removeEstablishment(int index) {
        establishments.remove(index);
    }

    public Card getHandCard(int index) {
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

    public void flipLandMark(Landmark card) {
        landmarks
                .stream()
                .filter(l -> l.equals(card) && l.isFlipped() == false)
                .findFirst()
                .map(targetlandmark -> {
                    targetlandmark.setFlipped(true);
                    return targetlandmark;
                })
                .orElseThrow(() -> new NoSuchElementException("This LandMark has been flipped"));
    }
}

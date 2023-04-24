package domain;

import domain.card.Card;
import domain.card.establishment.*;
import domain.card.landmark.*;
import org.apache.logging.log4j.util.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Marketplace {
    private final int IMPORTANT_ESTABLISHMENT_QUANTITY = 4;
    private final int ESTABLISHMENT_QUANTITY = 6;
    private List<Card> cards = new ArrayList<>();

    public Marketplace() {
        cards.addAll(generateCards(10, Bakery::new));
        cards.addAll(generateCards(10, WheatField::new));
        cards.addAll(generateCards(6, Cafe::new));
        cards.addAll(generateCards(6, AppleOrchard::new));
        cards.addAll(generateCards(6, CheeseFactory::new));
        cards.addAll(generateCards(6, ConvenienceStore::new));
        cards.addAll(generateCards(6, FamilyRestaurant::new));
        cards.addAll(generateCards(6, Forest::new));
        cards.addAll(generateCards(6, FruitAndVegetableMarket::new));
        cards.addAll(generateCards(6, FurnitureFactory::new));
        cards.addAll(generateCards(6, Mine::new));
        cards.addAll(generateCards(6, Ranch::new));

        cards.addAll(generateCards(4, Stadium::new));
        cards.addAll(generateCards(4, TvStation::new));
        cards.addAll(generateCards(4, BusinessCenter::new));

        cards.addAll(generateCards(4, AmusementPark::new));
        cards.addAll(generateCards(4, TrainStation::new));
        cards.addAll(generateCards(4, RadioTower::new));
        cards.addAll(generateCards(4, ShoppingMall::new));
    }

    private <T> List<T> generateCards(int amount, Supplier<T> cardSupplier) {
        return IntStream.range(0, amount)
                .mapToObj(index -> cardSupplier.get())
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }
}

package domain;

import domain.card.establishment.AppleOrchard;
import domain.card.establishment.Bakery;
import domain.card.establishment.BusinessCenter;
import domain.card.establishment.Cafe;
import domain.card.establishment.CheeseFactory;
import domain.card.establishment.ConvenienceStore;
import domain.card.establishment.Establishment;
import domain.card.establishment.FamilyRestaurant;
import domain.card.establishment.Forest;
import domain.card.establishment.FruitAndVegetableMarket;
import domain.card.establishment.FurnitureFactory;
import domain.card.establishment.Mine;
import domain.card.establishment.Ranch;
import domain.card.establishment.Stadium;
import domain.card.establishment.TvStation;
import domain.card.establishment.WheatField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marketplace {
    private final int IMPORTANT_ESTABLISHMENT_QUANTITY = 4;
    private final int ESTABLISHMENT_QUANTITY = 6;
    @Builder.Default
    private List<Establishment> establishments = new ArrayList<>();

    public void initial() {
        establishments.addAll(generateCards(10, Bakery::new));
        establishments.addAll(generateCards(10, WheatField::new));
        establishments.addAll(generateCards(6, Cafe::new));
        establishments.addAll(generateCards(6, AppleOrchard::new));
        establishments.addAll(generateCards(6, CheeseFactory::new));
        establishments.addAll(generateCards(6, ConvenienceStore::new));
        establishments.addAll(generateCards(6, FamilyRestaurant::new));
        establishments.addAll(generateCards(6, Forest::new));
        establishments.addAll(generateCards(6, FruitAndVegetableMarket::new));
        establishments.addAll(generateCards(6, FurnitureFactory::new));
        establishments.addAll(generateCards(6, Mine::new));
        establishments.addAll(generateCards(6, Ranch::new));

        establishments.addAll(generateCards(4, Stadium::new));
        establishments.addAll(generateCards(4, TvStation::new));
        establishments.addAll(generateCards(4, BusinessCenter::new));
    }

    private <T> List<T> generateCards(int amount, Supplier<T> cardSupplier) {
        return IntStream.range(0, amount)
                .mapToObj(index -> cardSupplier.get())
                .collect(Collectors.toList());
    }

    public List<Establishment> getEstablishments() {
        return establishments;
    }
}

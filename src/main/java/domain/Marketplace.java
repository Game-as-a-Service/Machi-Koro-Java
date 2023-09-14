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
import domain.exceptions.MachiKoroException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.logging.log4j.util.Supplier;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@AllArgsConstructor
public class Marketplace {
    private final int IMPORTANT_ESTABLISHMENT_QUANTITY = 4;
    private final int ESTABLISHMENT_QUANTITY = 6;
    //    @Builder.Default
//    private List<Establishment> establishments = new ArrayList<>();
    @Builder.Default
    private Map<String, List<Establishment>> establishmentMap = new HashMap<>();

    public Marketplace() {
//        establishments = new ArrayList<>();
        establishmentMap = new HashMap<>();
        initial();
    }

    public void initial() {
        establishmentMap.put(Bakery.class.getSimpleName(), generateCards(10, Bakery::new));
        establishmentMap.put(WheatField.class.getSimpleName(), generateCards(10, WheatField::new));
        establishmentMap.put(Cafe.class.getSimpleName(), generateCards(6, Cafe::new));
        establishmentMap.put(AppleOrchard.class.getSimpleName(), generateCards(6, AppleOrchard::new));
        establishmentMap.put(CheeseFactory.class.getSimpleName(), generateCards(6, CheeseFactory::new));
        establishmentMap.put(ConvenienceStore.class.getSimpleName(), generateCards(6, ConvenienceStore::new));
        establishmentMap.put(FamilyRestaurant.class.getSimpleName(), generateCards(6, FamilyRestaurant::new));
        establishmentMap.put(Forest.class.getSimpleName(), generateCards(6, Forest::new));
        establishmentMap.put(FruitAndVegetableMarket.class.getSimpleName(), generateCards(6, FruitAndVegetableMarket::new));
        establishmentMap.put(FurnitureFactory.class.getSimpleName(), generateCards(6, FurnitureFactory::new));
        establishmentMap.put(Mine.class.getSimpleName(), generateCards(6, Mine::new));
        establishmentMap.put(Ranch.class.getSimpleName(), generateCards(6, Ranch::new));
        establishmentMap.put(Stadium.class.getSimpleName(), generateCards(4, Stadium::new));
        establishmentMap.put(TvStation.class.getSimpleName(), generateCards(4, TvStation::new));
        establishmentMap.put(BusinessCenter.class.getSimpleName(), generateCards(4, BusinessCenter::new));

//        establishments.addAll(generateCards(10, Bakery::new));
//        establishments.addAll(generateCards(10, WheatField::new));
//        establishments.addAll(generateCards(6, Cafe::new));
//        establishments.addAll(generateCards(6, AppleOrchard::new));
//        establishments.addAll(generateCards(6, CheeseFactory::new));
//        establishments.addAll(generateCards(6, ConvenienceStore::new));
//        establishments.addAll(generateCards(6, FamilyRestaurant::new));
//        establishments.addAll(generateCards(6, Forest::new));
//        establishments.addAll(generateCards(6, FruitAndVegetableMarket::new));
//        establishments.addAll(generateCards(6, FurnitureFactory::new));
//        establishments.addAll(generateCards(6, Mine::new));
//        establishments.addAll(generateCards(6, Ranch::new));
//        establishments.addAll(generateCards(4, Stadium::new));
//        establishments.addAll(generateCards(4, TvStation::new));
//        establishments.addAll(generateCards(4, BusinessCenter::new));
    }

    private <T> List<T> generateCards(int amount, Supplier<T> cardSupplier) {
        return IntStream.range(0, amount)
                .mapToObj(index -> cardSupplier.get())
                .collect(Collectors.toList());
    }

//    public List<Establishment> getEstablishments() {
//        return establishments;
//    }

    public Map<String, List<Establishment>> getEstablishmentMap() {
        return establishmentMap;
    }

    public Establishment findEstablishmentByName(String cardName) {
        List<Establishment> matchEstablishments = establishmentMap.get(cardName);
        if (matchEstablishments.isEmpty()) {
            throw new MachiKoroException("建築藍圖已經沒有此建築物了，購買失敗");
        } else
            return matchEstablishments.get(0);
//        return establishments.stream().filter(establishment -> cardName.equals(establishment.getName())).findFirst().orElseThrow();
    }

    public void removeEstablishment(Establishment establishment) {
        List<Establishment> establishments = establishmentMap.get(establishment.getClass().getSimpleName());
        establishments.remove(establishment);
    }
}

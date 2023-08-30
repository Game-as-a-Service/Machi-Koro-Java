package spring.repositories.data;

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
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EstablishmentData {
    private Type type;

    public enum Type {
        APPLE_ORCHARD, BAKERY, BUSINESS_CENTER, CAFE,
        CHEESE_FACTORY, CONVENIENCE_STORE, FAMILY_RESTAURANT,
        FOREST, FRUIT_AND_VEGETABLE_MARKET, FURNITURE_FACTORY,
        MINE, RANCH, STADIUM, TV_STATION, WHEAT_FIELD
    }

    public Establishment toDomain() {
        return switch (type) {
            case APPLE_ORCHARD -> new AppleOrchard();
            case BAKERY -> new Bakery();
            case BUSINESS_CENTER -> new BusinessCenter();
            case CAFE -> new Cafe();
            case CHEESE_FACTORY -> new CheeseFactory();
            case CONVENIENCE_STORE -> new ConvenienceStore();
            case FAMILY_RESTAURANT -> new FamilyRestaurant();
            case FOREST -> new Forest();
            case FRUIT_AND_VEGETABLE_MARKET -> new FruitAndVegetableMarket();
            case FURNITURE_FACTORY -> new FurnitureFactory();
            case MINE -> new Mine();
            case RANCH -> new Ranch();
            case STADIUM -> new Stadium();
            case TV_STATION -> new TvStation();
            case WHEAT_FIELD -> new WheatField();
        };
    }

    public static EstablishmentData toData(Establishment establishment) {
        if (establishment instanceof AppleOrchard) {
            return toAppleOrchardData();
        } else if (establishment instanceof Bakery) {
            return toBakeryData();
        } else if (establishment instanceof BusinessCenter) {
            return toBusinessCenterData();
        } else if (establishment instanceof Cafe) {
            return toCafeData();
        } else if (establishment instanceof CheeseFactory) {
            return toCheeseFactoryData();
        } else if (establishment instanceof ConvenienceStore) {
            return toConvenienceStoreData();
        } else if (establishment instanceof FamilyRestaurant) {
            return toFamilyRestaurantData();
        } else if (establishment instanceof Forest) {
            return toForestData();
        } else if (establishment instanceof FruitAndVegetableMarket) {
            return toFruitAndVegetableMarketData();
        } else if (establishment instanceof FurnitureFactory) {
            return toFurnitureFactoryData();
        } else if (establishment instanceof Mine) {
            return toMineData();
        } else if (establishment instanceof Ranch) {
            return toRanchData();
        } else if (establishment instanceof Stadium) {
            return toStadiumData();
        } else if (establishment instanceof TvStation) {
            return toTvStationData();
        } else if (establishment instanceof WheatField) {
            return toWheatFieldData();
        } else {
            throw new RuntimeException("Unsupported establishment type: " + establishment.getClass());
        }
    }


    private static EstablishmentData toAppleOrchardData() {
        return EstablishmentData.builder()
                .type(Type.APPLE_ORCHARD)
                .build();
    }

    private static EstablishmentData toBakeryData() {
        return EstablishmentData.builder()
                .type(Type.BAKERY)
                .build();
    }

    private static EstablishmentData toBusinessCenterData() {
        return EstablishmentData.builder()
                .type(Type.BUSINESS_CENTER)
                .build();
    }

    private static EstablishmentData toCafeData() {
        return EstablishmentData.builder()
                .type(Type.CAFE)
                .build();
    }

    private static EstablishmentData toCheeseFactoryData() {
        return EstablishmentData.builder()
                .type(Type.CHEESE_FACTORY)
                .build();
    }

    private static EstablishmentData toConvenienceStoreData() {
        return EstablishmentData.builder()
                .type(Type.CONVENIENCE_STORE)
                .build();
    }

    private static EstablishmentData toFamilyRestaurantData() {
        return EstablishmentData.builder()
                .type(Type.FAMILY_RESTAURANT)
                .build();
    }

    private static EstablishmentData toForestData() {
        return EstablishmentData.builder()
                .type(Type.FOREST)
                .build();
    }

    private static EstablishmentData toFruitAndVegetableMarketData() {
        return EstablishmentData.builder()
                .type(Type.FRUIT_AND_VEGETABLE_MARKET)
                .build();
    }

    private static EstablishmentData toFurnitureFactoryData() {
        return EstablishmentData.builder()
                .type(Type.FURNITURE_FACTORY)
                .build();
    }

    private static EstablishmentData toMineData() {
        return EstablishmentData.builder()
                .type(Type.MINE)
                .build();
    }

    private static EstablishmentData toRanchData() {
        return EstablishmentData.builder()
                .type(Type.RANCH)
                .build();
    }

    private static EstablishmentData toStadiumData() {
        return EstablishmentData.builder()
                .type(Type.STADIUM)
                .build();
    }

    private static EstablishmentData toTvStationData() {
        return EstablishmentData.builder()
                .type(Type.TV_STATION)
                .build();
    }

    private static EstablishmentData toWheatFieldData() {
        return EstablishmentData.builder()
                .type(Type.WHEAT_FIELD)
                .build();
    }

}

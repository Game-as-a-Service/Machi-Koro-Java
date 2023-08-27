package spring.repositories.data;

import domain.card.landmark.AmusementPark;
import domain.card.landmark.Landmark;
import domain.card.landmark.RadioTower;
import domain.card.landmark.ShoppingMall;
import domain.card.landmark.TrainStation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LandmarkData {
    private boolean flipped;
    private Type type;
    public enum Type {
        AMUSEMENT_PARK, RADIO_TOWER, SHOPPING_MALL, TRAIN_STATION
    }

    public Landmark toDomain() {
        return switch (type) {
            case AMUSEMENT_PARK -> new AmusementPark();
            case RADIO_TOWER -> new RadioTower();
            case SHOPPING_MALL -> new ShoppingMall();
            case TRAIN_STATION -> new TrainStation();
        };
    }

    public static LandmarkData toData(Landmark landmark) {
        if (landmark instanceof AmusementPark a) {
            return toAmusementParkData(a);
        } else if (landmark instanceof RadioTower r) {
            return toRadioTowerData(r);
        } else if (landmark instanceof ShoppingMall s) {
            return toShoppingMallData(s);
        } else if (landmark instanceof TrainStation t) {
            return toTrainStationData(t);
        }
        throw new RuntimeException() ;
    }

    private static LandmarkData toAmusementParkData(AmusementPark amusementPark) {
        return LandmarkData.builder()
                .type(Type.AMUSEMENT_PARK)
                .flipped(amusementPark.isFlipped())
                .build();
    }

    private static LandmarkData toRadioTowerData(RadioTower radioTower) {
        return LandmarkData.builder()
                .type(Type.RADIO_TOWER)
                .flipped(radioTower.isFlipped())
                .build();
    }

    private static LandmarkData toShoppingMallData(ShoppingMall shoppingMall) {
        return LandmarkData.builder()
                .type(Type.SHOPPING_MALL)
                .flipped(shoppingMall.isFlipped())
                .build();
    }

    private static LandmarkData toTrainStationData(TrainStation trainStation) {
        return LandmarkData.builder()
                .type(Type.TRAIN_STATION)
                .flipped(trainStation.isFlipped())
                .build();
    }
}

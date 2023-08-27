package spring.repositories.data;

import domain.HandCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static domain.StreamUtils.mapToList;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HandCardData {
    protected List<EstablishmentData> establishments;
    protected List<LandmarkData> landmarks;

    public HandCard toDomain() {
        var establishments = mapToList(this.establishments, EstablishmentData::toDomain);
        var landmarks = mapToList(this.landmarks, LandmarkData::toDomain);
        return new HandCard(establishments, landmarks);
    }

    public static HandCardData toData(HandCard handCard) {
        var establishments = mapToList(handCard.getEstablishments(), EstablishmentData::toData);
        var landmarks = mapToList(handCard.getLandmarks(), LandmarkData::toData);
        return new HandCardData(establishments, landmarks);
    }
}

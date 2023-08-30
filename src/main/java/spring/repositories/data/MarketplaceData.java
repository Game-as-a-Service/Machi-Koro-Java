package spring.repositories.data;

import domain.Marketplace;
import lombok.Builder;

import java.util.List;

import static domain.StreamUtils.mapToList;

@Builder
public class MarketplaceData {
    private List<EstablishmentData> establishments;

    public Marketplace toDomain() {
        return Marketplace.builder()
                .establishments(mapToList(this.establishments, EstablishmentData::toDomain))
                .build();
    }

    public static MarketplaceData toData(Marketplace marketplace) {
        return MarketplaceData.builder()
                .establishments(mapToList(marketplace.getEstablishments(), EstablishmentData::toData))
                .build();
    }
}

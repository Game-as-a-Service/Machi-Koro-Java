package spring.repositories.data;

import domain.Marketplace;
import lombok.Builder;

import java.util.List;
import java.util.Map;

import static domain.StreamUtils.convertMapToAnotherMap;

@Builder
public class MarketplaceData {
    private Map<String, List<EstablishmentData>> establishmentMap;

    public Marketplace toDomain() {
        return Marketplace.builder()
                .establishmentMap(convertMapToAnotherMap(this.establishmentMap, EstablishmentData::toDomain))
                .build();
    }

    public static MarketplaceData toData(Marketplace marketplace) {
        return MarketplaceData.builder()
                .establishmentMap(convertMapToAnotherMap(marketplace.getEstablishmentMap(), EstablishmentData::toData))
                .build();
    }
}

package com.bc.ecommerce.infrastructure.db.springdata.mapper;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * PricesDboMapper interface. Mapstruct prices mapper to domain.
 * In com.bc.ecommerce.infrastructure.db.springdata.mapper.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@Mapper(componentModel = "spring")
public interface PricesDboMapper {

    /**
     * Map the given dbo prices to domain.
     * @param prices {@link PricesDbo} object.
     * @return The mapped dto object.
     */
     Prices map(PricesDbo prices);

    /**
     * Map the given dbo prices list to domain.
     * @param prices {@link PricesDbo} object.
     * @return The mapped dto object.
     */
    default Prices map(List<PricesDbo> prices) {
        Prices price = new Prices();
        return CollectionUtils.isEmpty(prices) ? price : this.map(prices.get(0));
    }

}

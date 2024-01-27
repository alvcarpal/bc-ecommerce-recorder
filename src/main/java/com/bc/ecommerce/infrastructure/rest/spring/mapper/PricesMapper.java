package com.bc.ecommerce.infrastructure.rest.spring.mapper;

import com.bc.ecommerce.application.mapper.DateUtilMapper;
import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.infrastructure.rest.spring.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * PricesMapper interface. Mapstruct prices mapper to dto.
 * com.bc.ecommerce.infrastructure.rest.spring.mapper.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@Mapper(componentModel = "spring", uses = DateUtilMapper.class)
public interface PricesMapper {

    /**
     * Map the given domain price to dto.
     * @param prices {@link PricesDbo} object.
     * @return The mapped dto object.
     */
    @Mapping(target = "startDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "endDate", qualifiedByName = "toOffsetDateTime")
    PriceDto map(Prices prices);

}

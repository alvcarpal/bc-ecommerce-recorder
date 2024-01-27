package com.bc.ecommerce.application.usescases;

import com.bc.ecommerce.domain.business.sql.QueryBuilder;
import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.domain.port.in.PricesService;
import com.bc.ecommerce.domain.port.out.PricesRepository;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import lombok.AllArgsConstructor;

/**
 * PricesService interface implementation.
 * In com.bc.ecommerce.application.usescases package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@AllArgsConstructor
public class PricesUseCase implements PricesService {

    private final PricesRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Prices search(PricesCriteria criteria) {
        CustomQuery query  = QueryBuilder.retrieve(criteria);
        return repository.pricesProjection(query);
    }

}

package com.bc.ecommerce.domain.port.out;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;

/**
 * PricesRepository class.
 * In com.bc.ecommerce.domain.port.out package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
public interface PricesRepository {

  /**
   * Builds and retrieves the price pvp detail for the given criteria.
   * @param customQuery The query to be executed by the repository.
   * @return The price pvp to be applied.
   */
  Prices pricesProjection(CustomQuery customQuery);

}

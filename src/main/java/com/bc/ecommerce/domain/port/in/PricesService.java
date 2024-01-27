package com.bc.ecommerce.domain.port.in;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;

/**
 * PricesService class.
 * In ccom.bc.ecommerce.domain.port.in package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
public interface PricesService {

  /**
   * Builds and retrieves the price pvp detail for the given criteria.
   * @param criteria The criteria to be applied.
   * @return The price pvp to be applied.
   */
  Prices search(PricesCriteria criteria);

}

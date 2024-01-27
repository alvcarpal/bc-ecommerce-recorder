package com.bc.ecommerce.domain.business.sql.filter;

import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;

/**
 * Price list filter.
 * In com.bc.ecommerce.domain.business.sql.filter package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class PriceListFilter extends SqlComposer<Integer> {

  /**
   * Creates a Price List Filter builder.
   *
   * @param sqlBuilder The SQL Builder.
   */
  public PriceListFilter(DefaultCustomQueryBuilder sqlBuilder) {
    super(sqlBuilder);
  }

  /**
   * Adds the criteria for the product id.
   *
   * @param priceList The price list integer.
   * @return The expression for the price list integer.
   */
  @Override
  public String apply(Integer priceList) {
    return sqlBuilder.eq(PricesTable.PRICE_LIST, priceList);
  }

}

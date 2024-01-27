package com.bc.ecommerce.domain.business.sql.filter;

import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;

/**
 * Product id filter.
 * In com.bc.ecommerce.domain.business.sql.filter package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class ProductIdFilter extends SqlComposer<String> {

  /**
   * Creates a Product Id Filter builder.
   *
   * @param sqlBuilder The SQL Builder.
   */
  public ProductIdFilter(DefaultCustomQueryBuilder sqlBuilder) {
    super(sqlBuilder);
  }

  /**
   * Adds the criteria for the product id.
   *
   * @param productId The product identifier.
   * @return The expression for the product id.
   */
  @Override
  public String apply(String productId) {
    return sqlBuilder.eq(PricesTable.PRODUCT_ID, productId);
  }

}

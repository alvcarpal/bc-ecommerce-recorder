package com.bc.ecommerce.infrastructure.db.springdata.sql.filter;

import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;

/**
 * Brand id filter.
 * In com.bc.ecommerce.infrastructure.db.springdata.sql.filter package.
 *
 * @author Álvaro Carmona
 * @since 28/01/2024
 */
public class BrandIdFilter extends SqlComposer<String> {

  /**
   * Creates a Brand Id Filter builder.
   *
   * @param sqlBuilder The SQL Builder.
   */
  public BrandIdFilter(DefaultCustomQueryBuilder sqlBuilder) {
    super(sqlBuilder);
  }

  /**
   * Adds the criteria for the brand id.
   *
   * @param brandId The brand identifier. Ex ZARA 1.
   * @return The expression for the brand id.
   */
  @Override
  public String apply(String brandId) {
    return sqlBuilder.eq(PricesTable.BRAND_ID, brandId);
  }

}

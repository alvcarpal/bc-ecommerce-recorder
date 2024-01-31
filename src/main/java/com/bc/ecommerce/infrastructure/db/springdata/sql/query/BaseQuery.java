package com.bc.ecommerce.infrastructure.db.springdata.sql.query;

import com.bc.ecommerce.infrastructure.db.springdata.sql.filter.BrandIdFilter;
import com.bc.ecommerce.infrastructure.db.springdata.sql.filter.DateIntervalFilter;
import com.bc.ecommerce.infrastructure.db.springdata.sql.filter.ProductIdFilter;
import com.bc.ecommerce.infrastructure.db.springdata.sql.filter.SqlComposer;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;
import java.time.OffsetDateTime;

/**
 * Base query configuration.
 * In com.bc.ecommerce.infrastructure.db.springdata.sql.query package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
abstract class BaseQuery<T> implements SqlBuilder<T> {

  protected final DefaultCustomQueryBuilder sqlBuilder;

  protected final SqlComposer<OffsetDateTime> dateIntervalComposer;
  protected final SqlComposer<String> productIdComposer;
  protected final SqlComposer<String> brandIdComposer;

  BaseQuery() {
    sqlBuilder = new DefaultCustomQueryBuilder();
    sqlBuilder.configureTableAlias(PricesTable.NAME, "p");

    this.dateIntervalComposer = new DateIntervalFilter(sqlBuilder);
    this.productIdComposer = new ProductIdFilter(sqlBuilder);
    this.brandIdComposer = new BrandIdFilter(sqlBuilder);
  }

}

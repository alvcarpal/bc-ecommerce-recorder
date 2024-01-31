package com.bc.ecommerce.infrastructure.db.springdata.sql.query;

import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;

/**
 * Select by criteria filter.
 * In com.bc.ecommerce.infrastructure.db.springdata.sql.query package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class SelectByCriteria extends BaseQuery<PricesCriteria> {

  /**
   * Creates the query for retrieving the price detail to be applied.
   */
  public SelectByCriteria() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CustomQuery build(PricesCriteria criteria) {
    return sqlBuilder.select(new PricesDbo(), PricesTable.NAME)
            .where(sqlBuilder.and(
                 productIdComposer.apply(criteria.getProductId()),
                 brandIdComposer.apply(criteria.getBrandId()),
                 dateIntervalComposer.apply(criteria.getIssueDate())
            )).sortBy(PricesTable.PRIORITY.getName())
            .limit();
  }

}

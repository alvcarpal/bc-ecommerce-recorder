package com.bc.ecommerce.domain.business.sql;

import com.bc.ecommerce.domain.business.sql.query.SelectByCriteria;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;

/**
 * Query builder class.
 * In com.bc.ecommerce.domain.business.sql package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class QueryBuilder {

  /**
   * Class for providing the dynamic prices SQL queries against the main datastore (H2).
   */
  private QueryBuilder() {
  }

  /**
   * Creates a query for retrieving the price pvp given a specific criteria.
   * @param filter The filter to apply.
   * @return The custom query.
   */
  public static CustomQuery retrieve(PricesCriteria filter) {
    return new QueryBuilder().retrieveQuery(filter);
  }

  /**
   * Creates a query for retrieving the price that matches the given criteria.
   * @param filter The filter to apply.
   * @return The custom query.
   */
  private CustomQuery retrieveQuery(PricesCriteria filter) {
    return new SelectByCriteria().build(filter);
  }

}

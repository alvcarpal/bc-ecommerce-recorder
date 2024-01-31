package com.bc.ecommerce.infrastructure.db.springdata.sql.query;

import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;

/**
 * Sql builder interface.
 * In com.bc.ecommerce.infrastructure.db.springdata.sql.query package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public interface SqlBuilder<T> {

  /**
   * Creates a sql query based on the T criteria.
   *
   * @param input The criteria.
   * @return The sql query.
   */
  CustomQuery build(T input);

}

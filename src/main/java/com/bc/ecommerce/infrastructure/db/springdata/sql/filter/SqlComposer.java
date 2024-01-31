package com.bc.ecommerce.infrastructure.db.springdata.sql.filter;

import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;

/**
 * Sql composer class.
 * In com.bc.ecommerce.infrastructure.db.springdata.sql.filter package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public abstract class SqlComposer<T> {

  protected final DefaultCustomQueryBuilder sqlBuilder;

  /**
   * A generic SQL Composer able to help in the building of a sql sentence.
   *
   * @param sqlBuilder The SQL Builder.
   */
  public SqlComposer(DefaultCustomQueryBuilder sqlBuilder) {
    this.sqlBuilder = sqlBuilder;
  }

  /**
   * Applies the proper method class to the given criteria in order to build the proper sql filter/part
   * of the target SQL query.
   *
   * @param criteria The criteria.
   * @return The SQL portion to append to the SQL query with the proper criteria values.
   */
  public abstract String apply(T criteria);

}

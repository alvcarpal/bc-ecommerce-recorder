package com.bc.ecommerce.infrastructure.db.springdata.model;

/**
 * Column builder test.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class TestColumnBuilder {

  public static Column of(String table, String column) {
    return Column.of(table, column);
  }

}

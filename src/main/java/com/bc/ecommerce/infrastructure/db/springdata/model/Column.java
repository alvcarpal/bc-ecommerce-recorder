package com.bc.ecommerce.infrastructure.db.springdata.model;

import lombok.Getter;

/**
 * Column class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Getter
public class Column {
  private String table;
  protected String name;
  private boolean uuidType;

  /**
   * Private constructor.
   */
  protected Column() {
  }

  static Column of(String table, String name) {
    Column column = new Column();
    column.table = table;
    column.name = name;
    return column;
  }

  static Column ofUuidType(String table, String name) {
    Column column = of(table, name);
    column.uuidType = true;
    return column;
  }

}

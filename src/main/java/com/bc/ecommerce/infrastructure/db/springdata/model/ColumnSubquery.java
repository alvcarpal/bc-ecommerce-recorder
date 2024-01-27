package com.bc.ecommerce.infrastructure.db.springdata.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Column subquery class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Getter
public class ColumnSubquery extends ColumnDerived {
  private String subquery;
  private List<Column> parentColumns = new ArrayList<>();

  /**
   * Private constructor.
   */
  private ColumnSubquery() {
    super();
  }

  /**
   * Creates a subquery column named as name, derived from the specified subquery.
   *
   * @param name The name of the derived column.
   * @param subquery The subquery generating the derived column.
   * @return The derived column.
   */
  public static ColumnSubquery of(String name, String subquery) {
    ColumnSubquery column = new ColumnSubquery();
    // the alias of the expression column
    column.name = name;
    column.subquery = subquery;
    return column;
  }

  /**
   * Creates a derived column named as name, derived from the specified expression, where
   * the given columns intervene.
   *
   * @param name The name of the derived column.
   * @param subquery The subquery generating the derived column.
   * @param parentColumns The columns from the parent query where the subquery is included.
   * @return The derived column.
   */
  public static ColumnSubquery of(String name, String subquery, Column...parentColumns) {
    ColumnSubquery column = of(name, subquery);
    column.parentColumns.addAll(Arrays.asList(parentColumns));
    return column;
  }
}

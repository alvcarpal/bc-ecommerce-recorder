package com.bc.ecommerce.infrastructure.db.springdata.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Column expression class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Getter
public class ColumnExpression extends ColumnDerived {
  private String expression;
  private List<Column> columnsInExpression = new ArrayList<>();

  /**
   * Private constructor.
   */
  private ColumnExpression() {
    super();
  }

  /**
   * Creates a derived column named as name, derived from the specified expression, where
   * the given columns intervene.
   *
   * @param name The name of the derived column.
   * @param expression The expression generating the derived column.
   * @return The derived column.
   */
  public static ColumnExpression of(String name, String expression) {
    ColumnExpression column = new ColumnExpression();
    // the alias of the expression column
    column.name = name;
    column.expression = expression;
    return column;
  }

  /**
   * Creates a derived column named as name, derived from the specified expression, where
   * the given columns intervene.
   *
   * @param name The name of the derived column.
   * @param expression The expression generating the derived column.
   * @param columnsInExpression The columns intervening in the expression.
   * @return The derived column.
   */
  public static ColumnExpression of(String name, String expression, Column...columnsInExpression) {
    ColumnExpression column = of(name, expression);
    column.columnsInExpression.addAll(Arrays.asList(columnsInExpression));
    return column;
  }
}

package com.bc.ecommerce.domain.business.sql.filter;

import com.bc.ecommerce.infrastructure.db.springdata.model.PricesTable;
import com.bc.ecommerce.infrastructure.db.springdata.query.DefaultCustomQueryBuilder;
import java.time.OffsetDateTime;

/**
 * Date interval filter.
 * In com.bc.ecommerce.domain.business.sql.filter package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class DateIntervalFilter extends SqlComposer<OffsetDateTime> {

  /**
   * Creates a Date Filter builder.
   *
   * @param sqlBuilder The SQL Builder.
   */
  public DateIntervalFilter(DefaultCustomQueryBuilder sqlBuilder) {
    super(sqlBuilder);
  }

  /**
   * Adds the criteria for the date interval filter.
   *
   * @param issueDate The issue date to be applied.
   * @return The expression for the date interval filter.
   */
  @Override
  public String apply(OffsetDateTime issueDate) {
    return issueDate == null ? null :
            sqlBuilder.parentheses(sqlBuilder.between(issueDate.toString(), PricesTable.START_DATE, PricesTable.END_DATE));
  }

}

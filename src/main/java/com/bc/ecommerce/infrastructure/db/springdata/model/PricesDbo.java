package com.bc.ecommerce.infrastructure.db.springdata.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * PricesDbo class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 * "Prices" is the entity that represents the retail prices to be
 * applied according to the interval marked between the start and
 * end dates. Additionally, in case two prices are found to be applicable
 * for a product, it applies the one with higher priority.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Entity
@Data
@Table(name = "prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productId", "priceList", "priority"})})
public class PricesDbo implements Projection {

  @Id
  private String id;

  @NotNull
  private String brandId;

  @NotNull
  private String productId;

  @NotNull
  private Integer priceList;

  @NotNull
  private Instant startDate;

  @NotNull
  private Instant endDate;

  @NotNull
  private BigDecimal price;

  @NotNull
  private String currency;

  @NotNull
  private Integer priority;

  @Override
  public List<Column> getColumns() {
    return List.of(
            PricesTable.ID,
            PricesTable.BRAND_ID,
            PricesTable.PRODUCT_ID,
            PricesTable.PRICE_LIST,
            PricesTable.START_DATE,
            PricesTable.END_DATE,
            PricesTable.PRICE,
            PricesTable.CURRENCY,
            PricesTable.PRIORITY
    );
  }

}

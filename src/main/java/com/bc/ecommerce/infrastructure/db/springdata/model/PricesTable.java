package com.bc.ecommerce.infrastructure.db.springdata.model;

/**
 * PricesTable class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
public class PricesTable {

  public static final String NAME = "public.prices";
  public static final Column ID = Column.of(NAME, "id");
  public static final Column BRAND_ID = Column.of(NAME, "brand_id");
  public static final Column PRODUCT_ID = Column.of(NAME, "product_id");
  public static final Column PRICE_LIST = Column.of(NAME, "price_list");
  public static final Column START_DATE = Column.of(NAME, "start_date");
  public static final Column END_DATE = Column.of(NAME, "end_date");
  public static final Column PRICE = Column.of(NAME, "price");
  public static final Column CURRENCY = Column.of(NAME, "currency");
  public static final Column PRIORITY = Column.of(NAME, "priority");

  private PricesTable() {
  }


}

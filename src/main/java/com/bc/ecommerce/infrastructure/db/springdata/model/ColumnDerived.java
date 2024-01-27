package com.bc.ecommerce.infrastructure.db.springdata.model;

import lombok.Getter;

/**
 * Column derived class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Getter
public abstract class ColumnDerived extends Column {

  /**
   * Protected constructor.
   */
  protected ColumnDerived() {
  }

}

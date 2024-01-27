package com.bc.ecommerce.infrastructure.db.springdata.model;

import java.util.List;

/**
 * Projection class.
 * In com.bc.ecommerce.infrastructure.db.springdata.model package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
public interface Projection {

  List<Column> getColumns();

}

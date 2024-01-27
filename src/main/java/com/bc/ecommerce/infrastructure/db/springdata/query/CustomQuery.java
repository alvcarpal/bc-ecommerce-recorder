package com.bc.ecommerce.infrastructure.db.springdata.query;

import com.bc.ecommerce.infrastructure.db.springdata.model.Projection;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Custom query interface.
 * In com.bc.ecommerce.infrastructure.db.springdata.query.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
public interface CustomQuery {

  /**
   * Executes the custom query and returns the result of the given type.
   *
   * @param entityManager The entity manager.
   * @param outClass The out class.
   * @param <T> The item result type.
   * @return The result.
   */
  <T extends Projection> List<T> doQuery(EntityManager entityManager, Class<T> outClass);

}

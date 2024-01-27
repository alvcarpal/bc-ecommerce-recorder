package com.bc.ecommerce.infrastructure.db.springdata.repository;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.domain.port.out.PricesRepository;
import com.bc.ecommerce.infrastructure.db.springdata.mapper.PricesDboMapper;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DefaultPricesRepository class.
 * In com.bc.ecommerce.infrastructure.db.springdata.repository package.
 *
 * @author Álvaro Carmona.
 * @since 27/01/2024
 */
@Repository
public class DefaultPricesRepository implements PricesRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private PricesDboMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public Prices pricesProjection(CustomQuery customQuery) {
    List<PricesDbo> dbos = customQuery.doQuery(entityManager, PricesDbo.class);
    return mapper.map(Objects.nonNull(dbos) ? dbos : new ArrayList<>());
  }

}

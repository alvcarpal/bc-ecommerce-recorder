package com.bc.ecommerce.domain.business.sql;

import com.bc.ecommerce.boot.spring.config.EcommerceRecorderSpringBootService;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.db.springdata.repository.DefaultPricesRepository;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceRecorderSpringBootService.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
public class PricesPrimaryQueriesIntegrationTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private DefaultPricesRepository repository;

  @Test
  public void testSelectNotPending() {
    PodamFactory podamFactory = new PodamFactoryImpl();
    PricesCriteria criteria = podamFactory.manufacturePojo(PricesCriteria.class);
    CustomQuery customQuery = QueryBuilder.retrieve(criteria);
    List<PricesDbo> result = customQuery.doQuery(entityManager, PricesDbo.class);
    Assert.assertTrue(result.isEmpty());
  }

}

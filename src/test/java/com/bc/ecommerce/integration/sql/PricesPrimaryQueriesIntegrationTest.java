package com.bc.ecommerce.integration.sql;

import com.bc.ecommerce.boot.spring.config.EcommerceRecorderSpringBootService;
import com.bc.ecommerce.domain.business.sql.QueryBuilder;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceRecorderSpringBootService.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@Sql(scripts = "/sql/fill_prices_relation.sql")
@Transactional
public class PricesPrimaryQueriesIntegrationTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private DefaultPricesRepository repository;

  @Test
  public void testPricesNotMatch() {
    PodamFactory podamFactory = new PodamFactoryImpl();
    PricesCriteria criteria = podamFactory.manufacturePojo(PricesCriteria.class);
    CustomQuery customQuery = QueryBuilder.retrieve(criteria);
    List<PricesDbo> result = customQuery.doQuery(entityManager, PricesDbo.class);
    Assert.assertTrue(result.isEmpty());
  }

  @Test
  public void testPricesWithMatchCriteria() {
    PodamFactory podamFactory = new PodamFactoryImpl();
    PricesCriteria criteria = podamFactory.manufacturePojo(PricesCriteria.class);
    criteria.setIssueDate(toOffsetDateTime("2020-06-14T09:31:26.075Z"));
    criteria.setProductId("35455");
    criteria.setBrandId("1");
    CustomQuery customQuery = QueryBuilder.retrieve(criteria);
    List<PricesDbo> result = customQuery.doQuery(entityManager, PricesDbo.class);
    Assert.assertFalse(result.isEmpty());
    Assert.assertEquals(1, result.size());
    Assert.assertEquals("35455", result.get(0).getProductId());
  }

  private static OffsetDateTime toOffsetDateTime(String date) {
    return OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

}

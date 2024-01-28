package com.bc.ecommerce.domain.business.sql;

import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import com.bc.ecommerce.utils.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest extends UnitTest {

  private PricesCriteria criteria;

  @Before
  public void setUp() {
    initializeFactory();
    criteria = factory.manufacturePojo(PricesCriteria.class);

  }

  @Test
  public void buildPricesWithOutNullFilterQuery() {
    CustomQuery prices = QueryBuilder.retrieve(criteria);
    Assert.assertNotNull(prices);
  }

  @Test
  public void buildPricesWithNullProductIdFilterQuery() {
    criteria.setProductId(null);
    CustomQuery prices = QueryBuilder.retrieve(criteria);
    Assert.assertNotNull(prices);
  }

  @Test
  public void buildPricesWithNullBrandIdFilterQuery() {
    criteria.setBrandId(null);
    CustomQuery prices = QueryBuilder.retrieve(criteria);
    Assert.assertNotNull(prices);
  }

  @Test
  public void buildPricesWithNullIssueDateFilterQuery() {
    criteria.setIssueDate(null);
    CustomQuery prices = QueryBuilder.retrieve(criteria);
    Assert.assertNotNull(prices);
  }

}

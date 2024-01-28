package com.bc.ecommerce.infrastructure.db.springdata.query;

import com.bc.ecommerce.infrastructure.db.springdata.model.Column;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.infrastructure.db.springdata.model.TestColumnBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Default custom query builder test class.
 * In com.bc.ecommerce.infrastructure.db.springdata.query.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
public class DefaultCustomQueryBuilderTest {

  private DefaultCustomQueryBuilder customQueryBuilder = new DefaultCustomQueryBuilder();

  @Test
  public void testSelectWithAlias() throws NoSuchFieldException, IllegalAccessException {
    customQueryBuilder.configureTableAlias("table", "t");
    List<Column> columns = List.of(TestColumnBuilder.of("table", "c1"), TestColumnBuilder.of("table", "c2"));
    customQueryBuilder.select(() -> columns,"table");
    assertEquals("select t.c1, t.c2 from table t", asSql());
  }

  @Test
  public void testEqExpr() throws NoSuchFieldException, IllegalAccessException {
    String expression = customQueryBuilder.eq(TestColumnBuilder.of("table", "c1"), "value");

    assertEquals("c1 = ?1", expression);
    assertTrue(paramIs(1, "value"));
  }

  @Test
  public void testEqWithAliasExpr() throws NoSuchFieldException, IllegalAccessException {
    customQueryBuilder.configureTableAlias("table", "t");
    String expression = customQueryBuilder.eq(TestColumnBuilder.of("table", "c1"), "value");

    assertEquals("t.c1 = ?1", expression);
    assertTrue(paramIs(1, "value"));
  }

  @Test
  public void testAndOnlyOneExpr() {
    String expression = customQueryBuilder.and("expr");

    assertEquals("expr", expression);
  }

  @Test
  public void testAndExpr() {
    String expression = customQueryBuilder.and("expr1", "expr2", "expr3");

    assertEquals("expr1 and expr2 and expr3", expression);
  }

  @Test
  public void testAndListOnlyOneExpr() {
    String expression = customQueryBuilder.and(List.of("expr"));

    assertEquals("expr", expression);
  }

  @Test
  public void testAndListExpr() {
    String expression = customQueryBuilder.and(List.of("expr1", "expr2", "expr3"));

    assertEquals("expr1 and expr2 and expr3", expression);
  }

  @Test
  public void testParenthesesExpr() {
    String expression = customQueryBuilder.parentheses("expr");

    assertEquals("(expr)", expression);
  }


  @Test
  public void testWhere() throws NoSuchFieldException, IllegalAccessException {
    customQueryBuilder.configureTableAlias("table", "t");
    List<Column> columns = List.of(TestColumnBuilder.of("table", "c1"), TestColumnBuilder.of("table", "c2"));
    customQueryBuilder.select(() -> columns,"table")
            .where(
                    customQueryBuilder.and(
                            customQueryBuilder.eq(TestColumnBuilder.of("table", "c1"), "value1"),
                            customQueryBuilder.eq(TestColumnBuilder.of("table", "c2"), "value2")
                    )
            );

    assertEquals("select t.c1, t.c2 from table t where t.c1 = ?1 and t.c2 = ?2", asSql());
    assertTrue(paramIs(1, "value1"));
    assertTrue(paramIs(2, "value2"));
  }

  @Test
  public void testSelectQuery() throws NoSuchFieldException, IllegalAccessException {
    EntityManager em = Mockito.mock(EntityManager.class);
    Query query = Mockito.mock(Query.class);
    Mockito.doReturn(query).when(em).createNativeQuery(Mockito.anyString(), Mockito.eq(PricesDbo.class));

    customQueryBuilder.configureTableAlias("table", "t");
    List<Column> columns = List.of(TestColumnBuilder.of("table", "c1"), TestColumnBuilder.of("table", "c2"));
    customQueryBuilder.select(() -> columns,"table")
            .where(
                    customQueryBuilder.and(
                            customQueryBuilder.eq(TestColumnBuilder.of("table", "c1"), "value1"),
                            customQueryBuilder.eq(TestColumnBuilder.of("table", "c2"), "value2")
                    )
            );
    CustomQuery customQuery = customQueryBuilder;
    customQuery.doQuery(em, PricesDbo.class);

    Mockito.verify(em, Mockito.times(1)).createNativeQuery(Mockito.eq(asSql()), Mockito.eq(PricesDbo.class));
    Mockito.verify(query, Mockito.times(1)).setParameter(Mockito.eq(1), Mockito.eq("value1"));
    Mockito.verify(query, Mockito.times(1)).setParameter(Mockito.eq(2), Mockito.eq("value2"));

  }

  private String asSql() throws NoSuchFieldException, IllegalAccessException {
    Field field = DefaultCustomQueryBuilder.class.getDeclaredField("queryBuilder");
    field.setAccessible(true);
    StringBuilder sb = (StringBuilder) field.get(customQueryBuilder);
    return sb.toString();
  }

  private boolean paramIs(int paramId, Object value) throws IllegalAccessException, NoSuchFieldException {
    Field field = DefaultCustomQueryBuilder.class.getDeclaredField("params");
    field.setAccessible(true);
    Map<String, Object> map = (Map<String, Object>) field.get(customQueryBuilder);
    return map.get(paramId).equals(value);
  }

}

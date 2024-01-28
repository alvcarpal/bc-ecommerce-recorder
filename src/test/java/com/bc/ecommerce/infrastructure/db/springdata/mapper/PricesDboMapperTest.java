package com.bc.ecommerce.infrastructure.db.springdata.mapper;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.db.springdata.model.PricesDbo;
import com.bc.ecommerce.utils.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesDboMapperTest extends UnitTest {

    private Prices prices;

    private PricesDbo pricesDbo;

    @Mock
    private PricesDboMapper mapper;

    @Before
    public void setUp() {
        initializeFactory();
        prices = factory.manufacturePojo(Prices.class);
        pricesDbo = factory.manufacturePojo(PricesDbo.class);
    }

    @Test
    public void testMapPricesDbo() {
        when(mapper.map(pricesDbo)).thenReturn(prices);
        Prices domain = mapper.map(pricesDbo);
        assertEquals(prices, domain);
    }

}

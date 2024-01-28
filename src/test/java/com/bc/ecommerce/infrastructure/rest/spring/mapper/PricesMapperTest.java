package com.bc.ecommerce.infrastructure.rest.spring.mapper;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.infrastructure.rest.spring.dto.PriceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesMapperTest {

    @Mock
    Prices prices;

    @Mock
    PriceDto priceDto;

    @Mock
    private PricesMapper mapper;

    @Test
    public void testMapPrices() {
        when(mapper.map(prices)).thenReturn(priceDto);
        PriceDto actualDto = mapper.map(prices);
        assertEquals(priceDto, actualDto);
    }

}

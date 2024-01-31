package com.bc.ecommerce.application.usecases;

import com.bc.ecommerce.application.usescases.PricesUseCase;
import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.domain.port.out.PricesRepository;
import com.bc.ecommerce.infrastructure.db.springdata.query.CustomQuery;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import com.bc.ecommerce.utils.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricesUseCaseTest extends UnitTest {

    @Mock
    private PricesRepository repository;

    @InjectMocks
    private PricesUseCase pricesUseCase;

    @Mock
    private CustomQuery customQuery;

    private PricesCriteria pricesCriteria;

    @Mock
    private Prices expectedPrices;

    @Before
    public void setUp() {
        initializeFactory();
        pricesCriteria = factory.manufacturePojo(PricesCriteria.class);
    }

    @Test
    public void testSearch() {
        when(repository.pricesProjection(any(PricesCriteria.class))).thenReturn(expectedPrices);
        Prices result = pricesUseCase.search(pricesCriteria);
        assertEquals(expectedPrices, result);
    }

}

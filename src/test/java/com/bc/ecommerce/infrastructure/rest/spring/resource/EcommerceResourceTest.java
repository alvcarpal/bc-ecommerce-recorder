package com.bc.ecommerce.infrastructure.rest.spring.resource;

import com.bc.ecommerce.domain.operational.Prices;
import com.bc.ecommerce.domain.port.in.PricesService;
import com.bc.ecommerce.infrastructure.rest.spring.dto.PriceDto;
import com.bc.ecommerce.infrastructure.rest.spring.mapper.PricesMapper;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import com.bc.ecommerce.utils.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EcommerceResourceTest extends UnitTest {

    @Mock
    private PricesService service;

    @Mock
    private PricesMapper mapper;

    @InjectMocks
    private EcommerceResource resource;

    @Mock
    private Prices prices;

    @Mock
    private PriceDto empty;

    private PriceDto response;

    @Before
    public void setUp() {
        initializeFactory();
        response = factory.manufacturePojo(PriceDto.class);

    }

    @Test
    public void pricesGet200OkTest() {
        doReturn(prices).when(service).search(any(PricesCriteria.class));
        doReturn(response).when(mapper).map(any(Prices.class));
        ResponseEntity<PriceDto> response = resource.getPrice("traceId", "Authorization", "35456", "1", OffsetDateTime.now());
        verify(service, times(1)).search(any(PricesCriteria.class));
        verify(mapper, times(1)).map(any(Prices.class));
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void pricesGet204NoContentTest() {
        doReturn(prices).when(service).search(any(PricesCriteria.class));
        doReturn(empty).when(mapper).map(any(Prices.class));
        ResponseEntity<PriceDto> response = resource.getPrice("traceId", "Authorization", "35456", "1", OffsetDateTime.now());
        verify(service, times(1)).search(any(PricesCriteria.class));
        verify(mapper, times(1)).map(any(Prices.class));
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}

package com.bc.ecommerce.infrastructure.rest.spring.resource;

import com.bc.ecommerce.domain.port.in.PricesService;
import com.bc.ecommerce.infrastructure.rest.spring.dto.PriceDto;
import com.bc.ecommerce.infrastructure.rest.spring.mapper.PricesMapper;
import com.bc.ecommerce.infrastructure.rest.spring.pojo.PricesCriteria;
import com.bc.ecommerce.infrastructure.rest.spring.spec.PriceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;

/**
 * EcommerceResource class. Rest controller ecommerce resource.
 * In com.bc.ecommerce.infrastructure.rest.spring.resource package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@RequiredArgsConstructor
@RestController
@Log4j2
@Api(tags = {"Ecommerce"})
public class EcommerceResource implements PriceApi {

    private final PricesService service;

    private final PricesMapper mapper;

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<PriceDto> getPrice(
            @ApiParam(required = true) @RequestHeader(value = "X-B3-TraceId") String xB3TraceId,
            @ApiParam(required = true) @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true) @RequestParam("product_id") String productId,
            @ApiParam(required = true) @RequestParam("price_list") Integer priceList,
            @ApiParam(required = true) @RequestParam("issue_date") OffsetDateTime issueDate
    ) {
        log.info("Price request start: product id {} , price list {} , issue date {}.", productId, priceList, issueDate.toString());
        PricesCriteria criteria = PricesCriteria.builder().priceList(priceList).productId(productId).issueDate(issueDate).build();
        var response = mapper.map(service.search(criteria));

        if (response != null && response.getProductId() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}

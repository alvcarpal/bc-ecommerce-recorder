package com.bc.ecommerce.domain.operational;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * "Prices" is the domain class that represents the retail prices to be applied
 * according to the interval marked between the start and end dates. Additionally,
 * in case two prices are found to be applicable for a product, it applies the one
 * with higher priority.
 */
@Data
@NoArgsConstructor
public class Prices {
    private String id;
    private String brandId;
    private String productId;
    private Integer priceList;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal price;
    private String currency;
    private Integer priority;
}

package com.bc.ecommerce.infrastructure.rest.spring.pojo;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

/**
 * Advanced search criteria for constructing the query with criteria builder.
 */
@Data
@Builder
public class PricesCriteria {
  private String productId;
  private String brandId;
  private OffsetDateTime issueDate;
}

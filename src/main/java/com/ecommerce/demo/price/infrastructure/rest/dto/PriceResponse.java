package com.ecommerce.demo.price.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PriceResponse {
  private Long productId;
  private Long  brandId;
  private Long priceList;
  private LocalDateTime date;
  private BigDecimal price;

}
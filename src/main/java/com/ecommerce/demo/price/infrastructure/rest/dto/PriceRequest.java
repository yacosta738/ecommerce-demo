package com.ecommerce.demo.price.infrastructure.rest.dto;

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
public class PriceRequest {
  private LocalDateTime date;
  private Long productId;
  private Long brandId;
}

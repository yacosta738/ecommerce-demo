package com.ecommerce.demo.price.domain;

import com.ecommerce.demo.common.domain.Money;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Price. This class represents a price of a product.
 * @author Yuniel Acosta
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
  private PriceId id;
  private PriceList priceList;
  private BrandId  brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private ProductId productId;
  private int priority;
  private Money price;
  private CurrencyType currencyType;
}

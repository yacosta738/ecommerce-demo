package com.ecommerce.demo.price.infrastructure.persistence.jpa.mapper;

import com.ecommerce.demo.price.domain.CurrencyType;
import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.entities.PriceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * PriceMapper. This class maps a PriceEntity to a Price.
 * @author Yuniel Acosta
 */
@Component
@Slf4j
public class PriceMapper {

  /**
   * Maps a PriceEntity to a Price. This method maps a PriceEntity to a Price.
   * @param priceEntity PriceEntity to be mapped.
   * @return Price mapped.
   */
  public Price priceEntityToPrice(PriceEntity priceEntity) {
    return Price.builder()
        .id(priceEntity.getId())
        .priceList(priceEntity.getPriceList())
        .brandId(priceEntity.getBrandId())
        .startDate(priceEntity.getStartDate())
        .endDate(priceEntity.getEndDate())
        .productId(priceEntity.getProductId())
        .priority(priceEntity.getPriority())
        .price(priceEntity.getPrice())
        .currencyType(CurrencyType.valueOf(priceEntity.getCurrencyType()))
        .build();
  }

  /**
   * Maps a Price to a PriceEntity. This method maps a Price to a PriceEntity.
   * @param price Price to be mapped.
   * @return PriceEntity mapped.
   */
  public PriceEntity priceToPriceEntity(Price price) {
    return PriceEntity.builder()
        .id(price.getId().value())
        .priceList(price.getPriceList().value())
        .brandId(price.getBrandId().value())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .productId(price.getProductId().value())
        .priority(price.getPriority())
        .price(price.getPrice().amount())
        .currencyType(price.getCurrencyType().name())
        .build();
  }
}

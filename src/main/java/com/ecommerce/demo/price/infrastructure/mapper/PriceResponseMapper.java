package com.ecommerce.demo.price.infrastructure.mapper;

import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceResponse;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceResponseMapper {

  public PriceResponse map(Price price, LocalDateTime applicationDate) {
    log.debug("Mapping price: {}", price);
    return PriceResponse.builder()
        .productId(price.getProductId().value())
        .brandId(price.getBrandId().value())
        .priceList(price.getPriceList().value())
        .date(applicationDate)
        .price(price.getPrice().amount())
        .build();
  }
}

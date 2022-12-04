package com.ecommerce.demo.price.infrastructure.service;

import com.ecommerce.demo.price.application.PriceFinder;
import com.ecommerce.demo.price.infrastructure.mapper.PriceResponseMapper;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceRequest;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceResponse;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceService {

  private final PriceFinder priceFinder;
  private final PriceResponseMapper priceResponseMapper;

  public PriceService(PriceFinder priceFinder, PriceResponseMapper priceResponseMapper) {
    this.priceFinder = priceFinder;
    this.priceResponseMapper = priceResponseMapper;
  }

  /**
   * Find price by application date and product id.
   *
   * @param priceRequest price request
   * @return price response
   */
  public Optional<PriceResponse> findPrice(PriceRequest priceRequest) {
    log.info("Request received: {}", priceRequest);
    return priceFinder.findPrice(priceRequest.getDate(), priceRequest.getProductId(),
        priceRequest.getBrandId())
        .map(price -> priceResponseMapper.map(price, priceRequest.getDate()));
  }
}

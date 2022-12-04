package com.ecommerce.demo.price.application;

import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.domain.PriceRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Price finder. This class is responsible for finding the price of a product.
 *
 * @author Yuniel Acosta
 */
public class PriceFinder {

  private final PriceRepository priceRepository;

  public PriceFinder(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  /**
   * Finds the price of a product.
   *
   * @param applicationDate the application date.
   * @param productId the product id.
   * @param brandId the brand id.
   * @return the price of the product.
   */
  public List<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return priceRepository.findPrice(applicationDate, productId, brandId);
  }
}

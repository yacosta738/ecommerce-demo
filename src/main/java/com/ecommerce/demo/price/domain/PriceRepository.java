package com.ecommerce.demo.price.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Price repository. This repository is responsible for retrieving prices from the database.
 * @author Yuniel Acosta
 */
public interface PriceRepository {

  /**
   * Finds the price of a product.
   * @param applicationDate the application date.
   * @param productId the product id.
   * @param brandId the brand id.
   * @return the price of the product.
   */
  List<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}

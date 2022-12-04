package com.ecommerce.demo.price.application;

import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.domain.PriceRepository;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
   * @param time to apply time.
   * @param productId the product id.
   * @param brandId the brand id.
   * @return the price of the product. If there is no price for the product, it returns an empty
   */
  public Optional<Price> findPrice(LocalDateTime time, Long productId, Long brandId) {
    validations(time, productId, brandId);

    List<Price> prices = priceRepository.findPrice(time, productId, brandId);
    return prices.stream().max(Price::compareTo);
  }

  private static void validations(LocalDateTime time, Long productId, Long brandId) {
    if (time == null) {
      throw new InvalidDateException("The application date is required");
    }

    if (productId == null) {
      throw new InvalidIdException("The product id is required");
    }

    if (brandId == null) {
      throw new InvalidIdException("The brand id is required");
    }
  }
}

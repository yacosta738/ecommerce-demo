package com.ecommerce.demo.price.infrastructure.persistence;

import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.domain.PriceRepository;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.JpaPriceRepository;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.mapper.PriceMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * H2 price repository.
 *
 * @author Yuniel Acosta
 */
@Repository
public class H2PriceRepository implements PriceRepository {

  private final JpaPriceRepository jpaPriceRepository;
  private final PriceMapper priceMapper;

  public H2PriceRepository(JpaPriceRepository jpaPriceRepository, PriceMapper priceMapper) {
    this.jpaPriceRepository = jpaPriceRepository;
    this.priceMapper = priceMapper;
  }

  /**
   * Finds the price of a product.
   *
   * @param applicationDate the application date.
   * @param productId       the product id.
   * @param brandId         the brand id.
   * @return the price of the product.
   */
  @Override
  public List<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return jpaPriceRepository.findPrice(productId, brandId, applicationDate)
        .stream()
        .map(priceMapper::priceEntityToPrice)
        .toList();
  }
}

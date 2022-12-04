package com.ecommerce.demo.integration.infastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecommerce.demo.integration.IntegrationTest;
import com.ecommerce.demo.price.domain.PriceRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class H2PriceRepositoryTest extends IntegrationTest {

  @Autowired
  private PriceRepository priceRepository;

  // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest1() {
    var price = priceRepository.findPrice(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L);
    assertNotNull(price);
    assertEquals(1, price.size());
    assertEquals(1, price.get(0).getBrandId().value());
    assertEquals(1, price.get(0).getPriceList().value());
    assertEquals(35455, price.get(0).getProductId().value());
    assertEquals(0, price.get(0).getPriority());
    assertEquals(35.50, price.get(0).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(0).getCurrencyType().value());
    assertEquals("2020-06-14T00:00", price.get(0).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(0).getEndDate().toString());
  }

  // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest2() {
    var price = priceRepository.findPrice(LocalDateTime.parse("2020-06-14T16:00:00"), 35455L, 1L);
    assertNotNull(price);
    assertEquals(2, price.size());
    assertEquals(1, price.get(0).getBrandId().value());
    assertEquals(1, price.get(0).getPriceList().value());
    assertEquals(35455, price.get(0).getProductId().value());
    assertEquals(0, price.get(0).getPriority());
    assertEquals(35.50, price.get(0).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(0).getCurrencyType().value());
    assertEquals("2020-06-14T00:00", price.get(0).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(0).getEndDate().toString());
    assertEquals(1, price.get(1).getBrandId().value());
    assertEquals(2, price.get(1).getPriceList().value());
    assertEquals(35455, price.get(1).getProductId().value());
    assertEquals(1, price.get(1).getPriority());
    assertEquals(25.45, price.get(1).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(1).getCurrencyType().value());
    assertEquals("2020-06-14T15:00", price.get(1).getStartDate().toString());
    assertEquals("2020-06-14T18:30", price.get(1).getEndDate().toString());

  }

  // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest3() {
    var price = priceRepository.findPrice(LocalDateTime.parse("2020-06-14T21:00:00"), 35455L, 1L);
    assertNotNull(price);
    assertEquals(1, price.size());
    assertEquals(1, price.get(0).getBrandId().value());
    assertEquals(1, price.get(0).getPriceList().value());
    assertEquals(35455, price.get(0).getProductId().value());
    assertEquals(0, price.get(0).getPriority());
    assertEquals(35.50, price.get(0).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(0).getCurrencyType().value());
    assertEquals("2020-06-14T00:00", price.get(0).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(0).getEndDate().toString());
  }

  // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest4() {
    var price = priceRepository.findPrice(LocalDateTime.parse("2020-06-15T10:00:00"), 35455L, 1L);
    assertNotNull(price);
    assertEquals(2, price.size());
    assertEquals(1, price.get(0).getBrandId().value());
    assertEquals(1, price.get(0).getPriceList().value());
    assertEquals(35455, price.get(0).getProductId().value());
    assertEquals(0, price.get(0).getPriority());
    assertEquals(35.50, price.get(0).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(0).getCurrencyType().value());
    assertEquals("2020-06-14T00:00", price.get(0).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(0).getEndDate().toString());
    assertEquals(1, price.get(1).getBrandId().value());
    assertEquals(3, price.get(1).getPriceList().value());
    assertEquals(35455, price.get(1).getProductId().value());
    assertEquals(1, price.get(1).getPriority());
    assertEquals(30.50, price.get(1).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(1).getCurrencyType().value());
    assertEquals("2020-06-15T00:00", price.get(1).getStartDate().toString());
    assertEquals("2020-06-15T11:00", price.get(1).getEndDate().toString());
  }

  // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest5() {
    var price = priceRepository.findPrice(LocalDateTime.parse("2020-06-16T21:00:00"), 35455L, 1L);
    assertNotNull(price);
    assertEquals(2, price.size());
    assertEquals(1, price.get(0).getBrandId().value());
    assertEquals(1, price.get(0).getPriceList().value());
    assertEquals(35455, price.get(0).getProductId().value());
    assertEquals(0, price.get(0).getPriority());
    assertEquals(35.50, price.get(0).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(0).getCurrencyType().value());
    assertEquals("2020-06-14T00:00", price.get(0).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(0).getEndDate().toString());
    assertEquals(1, price.get(1).getBrandId().value());
    assertEquals(4, price.get(1).getPriceList().value());
    assertEquals(35455, price.get(1).getProductId().value());
    assertEquals(1, price.get(1).getPriority());
    assertEquals(38.95, price.get(1).getPrice().amount().doubleValue());
    assertEquals("EUR", price.get(1).getCurrencyType().value());
    assertEquals("2020-06-15T16:00", price.get(1).getStartDate().toString());
    assertEquals("2020-12-31T23:59:59", price.get(1).getEndDate().toString());
  }
}

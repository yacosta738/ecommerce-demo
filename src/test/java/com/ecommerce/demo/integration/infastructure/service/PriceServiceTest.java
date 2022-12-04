package com.ecommerce.demo.integration.infastructure.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ecommerce.demo.integration.IntegrationTest;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceRequest;
import com.ecommerce.demo.price.infrastructure.service.PriceService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PriceServiceTest extends IntegrationTest {
  @Autowired
  private PriceService priceService;

  // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest1() {
    var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
        .productId(35455L)
        .brandId(1L)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    var priceResponse = optionalPriceResponse.get();
    assertEquals(1, priceResponse.getBrandId());
    assertEquals(1, priceResponse.getPriceList());
    assertEquals(35455, priceResponse.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T10:00"), priceResponse.getApplicationDate());
    assertEquals(35.50, priceResponse.getPrice().doubleValue());
  }

  // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest2() {
    var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse("2020-06-14T16:00:00"))
        .productId(35455L)
        .brandId(1L)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    var priceResponse = optionalPriceResponse.get();
    assertEquals(1, priceResponse.getBrandId());
    assertEquals(2, priceResponse.getPriceList());
    assertEquals(35455, priceResponse.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T16:00"), priceResponse.getApplicationDate());
    assertEquals(25.45, priceResponse.getPrice().doubleValue());
  }
  // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest3() {
    var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse("2020-06-14T21:00:00"))
        .productId(35455L)
        .brandId(1L)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    var priceResponse = optionalPriceResponse.get();
    assertEquals(1, priceResponse.getBrandId());
    assertEquals(1, priceResponse.getPriceList());
    assertEquals(35455, priceResponse.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T21:00"), priceResponse.getApplicationDate());
    assertEquals(35.50, priceResponse.getPrice().doubleValue());
  }

  // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest4() {
    var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse("2020-06-15T10:00:00"))
        .productId(35455L)
        .brandId(1L)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    var priceResponse = optionalPriceResponse.get();
    assertEquals(1, priceResponse.getBrandId());
    assertEquals(3, priceResponse.getPriceList());
    assertEquals(35455, priceResponse.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-15T10:00"), priceResponse.getApplicationDate());
    assertEquals(30.50, priceResponse.getPrice().doubleValue());
  }

  // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest5() {
    var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse("2020-06-16T21:00:00"))
        .productId(35455L)
        .brandId(1L)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    var priceResponse = optionalPriceResponse.get();
    assertEquals(1, priceResponse.getBrandId());
    assertEquals(4, priceResponse.getPriceList());
    assertEquals(35455, priceResponse.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-16T21:00"), priceResponse.getApplicationDate());
    assertEquals(38.95, priceResponse.getPrice().doubleValue());
  }
}

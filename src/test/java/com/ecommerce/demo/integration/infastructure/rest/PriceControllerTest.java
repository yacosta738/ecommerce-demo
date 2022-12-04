package com.ecommerce.demo.integration.infastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecommerce.demo.integration.TestController;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

class PriceControllerTest extends TestController {

  // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest1() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-14T10:00:00&productId=35455&brandId=1",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    var price = response.getBody();
    assertNotNull(price);
    assertEquals(1, price.getBrandId());
    assertEquals(1, price.getPriceList());
    assertEquals(35455, price.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T10:00"), price.getDate());
    assertEquals(35.50, price.getPrice().doubleValue());
  }

  // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest2() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-14T16:00:00&productId=35455&brandId=1",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    var price = response.getBody();
    assertNotNull(price);
    assertEquals(1, price.getBrandId());
    assertEquals(2, price.getPriceList());
    assertEquals(35455, price.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T16:00"), price.getDate());
    assertEquals(25.45, price.getPrice().doubleValue());
  }

  // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest3() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-14T21:00:00&productId=35455&brandId=1",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    var price = response.getBody();
    assertNotNull(price);
    assertEquals(1, price.getBrandId());
    assertEquals(1, price.getPriceList());
    assertEquals(35455, price.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-14T21:00"), price.getDate());
    assertEquals(35.50, price.getPrice().doubleValue());
  }

  // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest4() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-15T10:00:00&productId=35455&brandId=1",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    var price = response.getBody();
    assertNotNull(price);
    assertEquals(1, price.getBrandId());
    assertEquals(3, price.getPriceList());
    assertEquals(35455, price.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-15T10:00"), price.getDate());
    assertEquals(30.50, price.getPrice().doubleValue());
  }

  // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  @Test
  void findPriceByProductIdAndBrandIdAndDateTest5() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-16T21:00:00&productId=35455&brandId=1",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    var price = response.getBody();
    assertNotNull(price);
    assertEquals(1, price.getBrandId());
    assertEquals(4, price.getPriceList());
    assertEquals(35455, price.getProductId());
    assertEquals(LocalDateTime.parse("2020-06-16T21:00"), price.getDate());
    assertEquals(38.95, price.getPrice().doubleValue());
  }

  @Test
  void notFoundPriceByProductIdAndBrandIdAndDateTest() {
    ResponseEntity<PriceResponse> response =
        this.testRestTemplate.exchange(
            "/api/price?date=2020-06-16T21:00:00&productId=35455&brandId=2",
            HttpMethod.GET,
            this.getDefaultRequestEntity(),
            PriceResponse.class);
    assertNotNull(response);
    assertEquals(404, response.getStatusCode().value());
  }
}

package com.ecommerce.demo.unit.price.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.demo.price.application.PriceFinder;
import com.ecommerce.demo.price.domain.CurrencyType;
import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.infrastructure.mapper.PriceResponseMapper;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceRequest;
import com.ecommerce.demo.price.infrastructure.service.PriceService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceServiceTest {

  private static final String NOW = "2020-06-14T10:15:30";
  private static final Long PRODUCT_ID = 1L;
  private static final long BRAND_ID = 1L;
  private PriceService priceService;

  @BeforeEach
  void setUp() {
    PriceFinder priceFinder = mock(PriceFinder.class);
    final PriceResponseMapper priceResponseMapper = new PriceResponseMapper();
    priceService = new PriceService(priceFinder, priceResponseMapper);
    when(priceFinder.findPrice(LocalDateTime.parse(NOW), PRODUCT_ID, BRAND_ID))
        .thenReturn(
            Optional.of(
                Price.builder()
                    .id(1L)
                    .priceList(1L)
                    .brandId(BRAND_ID)
                    .startDate(LocalDateTime.parse("2020-05-14T10:15:30"))
                    .endDate(LocalDateTime.parse("2020-07-14T10:15:30"))
                    .productId(PRODUCT_ID)
                    .priority(1)
                    .price(new BigDecimal("34.87"))
                    .currencyType(CurrencyType.GBP)
                    .build()
            )
        );
  }

  @Test
  void findPrice() {
    final var optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .date(LocalDateTime.parse(NOW))
        .productId(PRODUCT_ID)
        .brandId(BRAND_ID)
        .build());
    assertNotNull(optionalPriceResponse);
    assertTrue(optionalPriceResponse.isPresent());
    final var priceResponse = optionalPriceResponse.get();
    assertEquals(1L, priceResponse.getProductId());
    assertEquals(1L, priceResponse.getBrandId());
    assertEquals(1L, priceResponse.getPriceList());
    assertEquals(LocalDateTime.parse(NOW), priceResponse.getDate());
    assertEquals(new BigDecimal("34.87"), priceResponse.getPrice());
  }

  @Test
  void findPriceNotFound() {
    final var priceResponse = priceService.findPrice(PriceRequest.builder()
        .date(LocalDateTime.parse(NOW))
        .productId(2L)
        .brandId(BRAND_ID)
        .build());
    assertNotNull(priceResponse);
    assertFalse(priceResponse.isPresent());
  }

  @Test
  void findPriceNotFoundByDate() {
    final var priceResponse = priceService.findPrice(PriceRequest.builder()
        .date(LocalDateTime.parse("2000-08-14T10:15:30"))
        .productId(PRODUCT_ID)
        .brandId(BRAND_ID)
        .build());
    assertNotNull(priceResponse);
    assertFalse(priceResponse.isPresent());
  }
}

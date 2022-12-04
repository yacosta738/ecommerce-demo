package com.ecommerce.demo.unit.price.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.demo.BaseTestMock;
import com.ecommerce.demo.price.application.InvalidDateException;
import com.ecommerce.demo.price.application.PriceFinder;
import com.ecommerce.demo.price.domain.CurrencyType;
import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.domain.PriceRepository;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceFinderTest extends BaseTestMock {

  private static final long PRODUCT_ID = 1L;
  private static final long BRAND_ID = 1L;
  private static final String NOW = "2020-05-10T11:15:30";
  private PriceFinder priceFinder;

  @BeforeEach
  void setUp() {
    givenFixedDate(LocalDateTime.parse(NOW));
    Price price = Price.builder()
        .id(1L)
        .priceList(1L)
        .brandId(BRAND_ID)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.parse("2020-06-14T10:15:30"))
        .productId(PRODUCT_ID)
        .priority(1)
        .price(new BigDecimal("34.87"))
        .currencyType(CurrencyType.EUR)
        .build();
    PriceRepository priceRepository = mock(PriceRepository.class);
    when(priceRepository.findPrice(LocalDateTime.now(), PRODUCT_ID, BRAND_ID)).thenReturn(
        List.of(price));
    priceFinder = new PriceFinder(priceRepository);
  }

  @Test
  void findPrice() {
    var optionalPrice = priceFinder.findPrice(LocalDateTime.now(), PRODUCT_ID, BRAND_ID);
    assertNotNull(optionalPrice);
    assertTrue(optionalPrice.isPresent());
    assertEquals(1L, optionalPrice.get().getId().value());
    assertEquals(1L, optionalPrice.get().getPriceList().value());
    assertEquals(BRAND_ID, optionalPrice.get().getBrandId().value());
    assertEquals(PRODUCT_ID, optionalPrice.get().getProductId().value());
    assertEquals(1, optionalPrice.get().getPriority());
    assertEquals("34.87", optionalPrice.get().getPrice().amount().toString());
    assertEquals("EUR", optionalPrice.get().getCurrencyType().value());
    assertEquals(LocalDateTime.parse(NOW), optionalPrice.get().getStartDate());
    assertEquals(LocalDateTime.parse("2020-06-14T10:15:30"), optionalPrice.get().getEndDate());
  }

  @Test
  void findPriceNotFound() {
    var optionalPrice = priceFinder.findPrice(LocalDateTime.now(), 2L, 2L);
    assertNotNull(optionalPrice);
    assertFalse(optionalPrice.isPresent());
  }

  @Test
  void findPriceWithNullApplicationDate() {
    assertThrows(InvalidDateException.class, () -> priceFinder.findPrice(null, 2L, 2L));
  }

  @Test
  void findPriceWithNullProductId() {
    assertThrows(InvalidIdException.class,
        () -> priceFinder.findPrice(LocalDateTime.now(), null, 2L));
  }

  @Test
  void findPriceWithNullBrandId() {
    assertThrows(InvalidIdException.class,
        () -> priceFinder.findPrice(LocalDateTime.now(), 2L, null));
  }

  @Test
  void findPriceWithHighPriority() {
    Price price = Price.builder()
        .id(1L)
        .priceList(1L)
        .brandId(BRAND_ID)
        .startDate(LocalDateTime.parse("2020-04-20T10:15:30"))
        .endDate(LocalDateTime.parse("2020-06-14T10:15:30"))
        .productId(PRODUCT_ID)
        .priority(2)
        .price(new BigDecimal("34.87"))
        .currencyType(CurrencyType.EUR)
        .build();
    Price price2 = Price.builder()
        .id(2L)
        .priceList(2L)
        .brandId(BRAND_ID)
        .startDate(LocalDateTime.parse("2020-03-14T10:15:30"))
        .endDate(LocalDateTime.parse("2020-07-14T10:15:30"))
        .productId(PRODUCT_ID)
        .priority(1)
        .price(new BigDecimal("35.87"))
        .currencyType(CurrencyType.EUR)
        .build();
    PriceRepository priceRepository = mock(PriceRepository.class);
    when(priceRepository.findPrice(LocalDateTime.now(), PRODUCT_ID, BRAND_ID)).thenReturn(
        List.of(price, price2));
    priceFinder = new PriceFinder(priceRepository);
    var optionalPrice = priceFinder.findPrice(LocalDateTime.now(), PRODUCT_ID, BRAND_ID);
    assertNotNull(optionalPrice);
    assertTrue(optionalPrice.isPresent());
    assertEquals(1L, optionalPrice.get().getId().value());
    assertEquals(1L, optionalPrice.get().getPriceList().value());
    assertEquals(BRAND_ID, optionalPrice.get().getBrandId().value());
    assertEquals(PRODUCT_ID, optionalPrice.get().getProductId().value());
    assertEquals(2, optionalPrice.get().getPriority());
    assertEquals("34.87", optionalPrice.get().getPrice().amount().toString());
    assertEquals("EUR", optionalPrice.get().getCurrencyType().value());
    assertEquals(LocalDateTime.parse("2020-04-20T10:15:30"), optionalPrice.get().getStartDate());
    assertEquals(LocalDateTime.parse("2020-06-14T10:15:30"), optionalPrice.get().getEndDate());
  }
}

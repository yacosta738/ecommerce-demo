package com.ecommerce.demo.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.demo.BaseTestMock;
import com.ecommerce.demo.price.domain.CurrencyType;
import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.domain.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class PriceTest extends BaseTestMock {

  public static final String DATE = "2021-08-09T14:50:42";

  @Test
  void createPrice() {
    givenFixedDate(LocalDateTime.parse(DATE));
    Price price = Price.builder()
        .id(1L)
        .priceList(1L)
        .brandId(1L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("35.50"))
        .currencyType(CurrencyType.EUR)
        .build();
    assertNotNull(price);
    assertEquals(1L, price.getId().value());
    assertEquals(1L, price.getPriceList().value());
    assertEquals(1L, price.getBrandId().value());
    assertEquals(DATE, price.getStartDate().toString());
    assertEquals(DATE, price.getEndDate().toString());
    assertEquals(35455L, price.getProductId().value());
    assertEquals(1, price.getPriority());
    assertEquals(new BigDecimal("35.50"), price.getPrice().amount());
    assertEquals(CurrencyType.EUR, price.getCurrencyType());
  }

  @Test
  void comparePrice() {
    givenFixedDate(LocalDateTime.parse(DATE));
    Price price = Price.builder()
        .id(1L)
        .priceList(1L)
        .brandId(1L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("35.50"))
        .currencyType(CurrencyType.EUR)
        .build();
    Price price2 = Price.builder()
        .id(1L)
        .priceList(1L)
        .brandId(1L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("35.50"))
        .currencyType(CurrencyType.EUR)
        .build();
    assertEquals(price, price2);
    Price price3 = Price.builder()
        .id(10L)
        .priceList(9L)
        .brandId(2L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .productId(351255L)
        .priority(0)
        .price(new BigDecimal("15.80"))
        .currencyType(CurrencyType.USD)
        .build();
    assertNotEquals(price, price3);
    List<Price> prices = new java.util.ArrayList<>(List.of(price, price2, price3));
    // sort by price
    prices.sort(Price::compareTo);
    assertEquals(price3, prices.get(0));
  }

  @Test
  void createPriceWithNullIdOrNegative() {
    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .priceList(1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });

    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(-1L)
          .priceList(1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });
  }

  @Test
  void createPriceWithNullPriceListOrNegative() {
    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });

    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .priceList(-1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });
  }

  @Test
  void createPriceWithNullBrandIdOrNegative() {
    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .priceList(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });

    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .priceList(1L)
          .brandId(-1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });
  }

  @Test
  void createPriceWithNullProductIdOrNegative() {
    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .priceList(1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });

    assertThrows(ValidationException.class, () -> {
      Price.builder()
          .id(1L)
          .priceList(1L)
          .brandId(1L)
          .startDate(LocalDateTime.now())
          .endDate(LocalDateTime.now())
          .productId(-1L)
          .priority(1)
          .price(new BigDecimal("1.0"))
          .currencyType(CurrencyType.EUR)
          .build();
    });
  }
}

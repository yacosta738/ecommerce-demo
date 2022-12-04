package com.ecommerce.demo.unit.price.infrastructure.persistence.jpa.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ecommerce.demo.BaseTestMock;
import com.ecommerce.demo.price.domain.CurrencyType;
import com.ecommerce.demo.price.domain.Price;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.entities.PriceEntity;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.mapper.PriceMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceMapperTest extends BaseTestMock {

  public static final String NOW = "2020-06-14T10:15:30";
  private PriceMapper priceMapper;
  private static final long ID = 1L;
  private static final long PRICE_LIST = 1L;
  private static final long BRAND_ID = 1L;
  private static final long PRODUCT_ID = 1L;
  private static final int PRIORITY = 1;
  private static final String PRICE = "34.87";
  private static final String CURR = "EUR";

  @BeforeEach
  void setUp() {
    priceMapper = new PriceMapper();
  }

  @Test
  void priceEntityToPrice() {
    givenFixedDate(LocalDateTime.parse(NOW));
    var priceEntity = PriceEntity.builder()
        .id(ID)
        .priceList(PRICE_LIST)
        .brandId(BRAND_ID)
        .productId(PRODUCT_ID)
        .priority(PRIORITY)
        .price(new BigDecimal(PRICE))
        .currencyType(CURR)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .build();

    var price = priceMapper.priceEntityToPrice(priceEntity);
    
    assertEquals(ID, price.getId().value());
    assertEquals(PRICE_LIST, price.getPriceList().value());
    assertEquals(BRAND_ID, price.getBrandId().value());
    assertEquals(PRODUCT_ID, price.getProductId().value());
    assertEquals(PRIORITY, price.getPriority());
    assertEquals(PRICE, price.getPrice().amount().toString());
    assertEquals(CURR, price.getCurrencyType().value());
    assertEquals(LocalDateTime.parse(NOW), price.getStartDate());
    assertEquals(LocalDateTime.parse(NOW), price.getEndDate());
    
  }

  @Test
  void priceToPriceEntity() {
    givenFixedDate(LocalDateTime.parse(NOW));
    var price = Price.builder()
        .id(ID)
        .priceList(PRICE_LIST)
        .brandId(BRAND_ID)
        .productId(PRODUCT_ID)
        .priority(PRIORITY)
        .price(new BigDecimal(PRICE))
        .currencyType(CurrencyType.valueOf(CURR))
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .build();

    var priceEntity = priceMapper.priceToPriceEntity(price);

    assertEquals(ID, priceEntity.getId());
    assertEquals(PRICE_LIST, priceEntity.getPriceList());
    assertEquals(BRAND_ID, priceEntity.getBrandId());
    assertEquals(PRODUCT_ID, priceEntity.getProductId());
    assertEquals(PRIORITY, priceEntity.getPriority());
    assertEquals(PRICE, priceEntity.getPrice().toString());
    assertEquals(CURR, priceEntity.getCurrencyType());
    assertEquals(LocalDateTime.parse(NOW), priceEntity.getStartDate());
    assertEquals(LocalDateTime.parse(NOW), priceEntity.getEndDate());
  }
}
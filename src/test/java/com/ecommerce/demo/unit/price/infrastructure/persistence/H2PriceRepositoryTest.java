package com.ecommerce.demo.unit.price.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.demo.BaseTestMock;
import com.ecommerce.demo.price.domain.PriceRepository;
import com.ecommerce.demo.price.infrastructure.persistence.H2PriceRepository;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.JpaPriceRepository;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.entities.PriceEntity;
import com.ecommerce.demo.price.infrastructure.persistence.jpa.mapper.PriceMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2PriceRepositoryTest extends BaseTestMock {

  private static final long ID = 1L;
  private static final long PRODUCT_ID = 1L;
  private static final long PRODUCT_LIST = 1L;
  private static final long BRAND_ID = 1L;
  private static final int PRIORITY = 1;
  private static final String PRICE = "34.87";
  private static final String NOW = "2020-05-10T11:15:30";
  private PriceRepository priceRepository;

  @BeforeEach
  void setUp() {
    final JpaPriceRepository jpaPriceRepository = mock(JpaPriceRepository.class);
    final PriceMapper priceMapper = new PriceMapper();
    priceRepository = new H2PriceRepository(jpaPriceRepository, priceMapper);
    when(jpaPriceRepository.findPrice(PRODUCT_ID, BRAND_ID, LocalDateTime.parse(NOW)))
        .thenReturn(List.of(PriceEntity.builder()
                .id(ID)
                .priceList(PRODUCT_LIST)
                .brandId(BRAND_ID)
                .startDate(LocalDateTime.parse(NOW))
                .endDate(LocalDateTime.parse("2020-06-14T10:15:30"))
                .productId(PRODUCT_ID)
                .priority(PRIORITY)
                .price(new BigDecimal(PRICE))
                .currencyType("EUR")
                .build(),
            PriceEntity.builder()
                .id(ID)
                .priceList(PRODUCT_LIST)
                .brandId(BRAND_ID)
                .startDate(LocalDateTime.parse("2020-03-14T10:15:30"))
                .endDate(LocalDateTime.parse("2020-07-14T10:15:30"))
                .productId(PRODUCT_ID)
                .priority(2)
                .price(new BigDecimal(PRICE))
                .currencyType("EUR")
                .build()
        ));
  }

  @Test
  void findPrice() {
    givenFixedDate(LocalDateTime.parse(NOW));
    final var prices = priceRepository.findPrice(LocalDateTime.parse(NOW), PRODUCT_ID, BRAND_ID);
    assertNotNull(prices);
    assertFalse(prices.isEmpty());
    assertEquals(2, prices.size());
    assertEquals(ID, prices.get(0).getId().value());
    assertEquals(PRODUCT_ID, prices.get(0).getProductId().value());
    assertEquals(BRAND_ID, prices.get(0).getBrandId().value());
    assertEquals(PRICE, prices.get(0).getPrice().amount().toString());
    assertEquals(LocalDateTime.now(), prices.get(0).getStartDate());
    assertEquals(LocalDateTime.parse("2020-06-14T10:15:30"), prices.get(0).getEndDate());
    assertEquals(PRIORITY, prices.get(0).getPriority());
    assertEquals("EUR", prices.get(0).getCurrencyType().value());

    assertEquals(ID, prices.get(1).getId().value());
    assertEquals(PRODUCT_ID, prices.get(1).getProductId().value());
    assertEquals(BRAND_ID, prices.get(1).getBrandId().value());
    assertEquals(PRICE, prices.get(1).getPrice().amount().toString());
    assertEquals(LocalDateTime.parse("2020-03-14T10:15:30"), prices.get(1).getStartDate());
    assertEquals(LocalDateTime.parse("2020-07-14T10:15:30"), prices.get(1).getEndDate());
    assertEquals(2, prices.get(1).getPriority());
    assertEquals("EUR", prices.get(1).getCurrencyType().value());
  }

  @Test
  void findPriceNotFound() {
    givenFixedDate(LocalDateTime.parse(NOW));
    final var prices = priceRepository.findPrice(LocalDateTime.parse(NOW), 2L, 2L);
    assertNotNull(prices);
    assertTrue(prices.isEmpty());
  }

  @Test
  void findPriceNotFoundByDate() {
    givenFixedDate(LocalDateTime.parse(NOW));
    final var prices = priceRepository.findPrice(LocalDateTime.parse("2000-05-10T11:15:31"), PRODUCT_ID, BRAND_ID);
    assertNotNull(prices);
    assertTrue(prices.isEmpty());
  }
}

package com.ecommerce.demo.unit.price.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.demo.price.infrastructure.rest.PriceController;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceRequest;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceResponse;
import com.ecommerce.demo.price.infrastructure.service.PriceService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceControllerTest {

  private static final String NOW = "2020-06-14T10:15:30";
  private static final Long PRODUCT_ID = 1L;
  private static final long BRAND_ID = 1L;
  private PriceController priceController;

  @BeforeEach
  void setUp() {
    PriceService priceService = mock(PriceService.class);
    priceController = new PriceController(priceService);
    when(priceService.findPrice(PriceRequest.builder()
        .date(LocalDateTime.parse(NOW))
        .brandId(BRAND_ID)
        .productId(PRODUCT_ID)
        .build()))
        .thenReturn(
            Optional.of(
                PriceResponse.builder()
                    .date(LocalDateTime.parse(NOW))
                    .brandId(BRAND_ID)
                    .productId(PRODUCT_ID)
                    .priceList(1L)
                    .price(new BigDecimal("34.87"))
                    .build()
            )
        );
  }

  @Test
  void findPrice() {
    final var response = priceController.findPrice(
        NOW,
        PRODUCT_ID,
        BRAND_ID
    );
    assertNotNull(response);
    assertTrue(response.getStatusCode().is2xxSuccessful());
    PriceResponse body = response.getBody();
    assertNotNull(body);
    assertEquals(PRODUCT_ID, body.getProductId());
    assertEquals(BRAND_ID, body.getBrandId());
    assertEquals(LocalDateTime.parse(NOW), body.getDate());
    assertEquals(new BigDecimal("34.87"), body.getPrice());
    assertEquals(1L, body.getPriceList());
  }

  @Test
  void findPriceNotFound() {
    final var response = priceController.findPrice(
        NOW,
        2L,
        2L
    );
    assertNotNull(response);
    assertTrue(response.getStatusCode().is4xxClientError());
  }

  @Test
  void findPriceNotFoundByDate() {
    final var response = priceController.findPrice(
        "2020-06-14T10:15:31",
        PRODUCT_ID,
        BRAND_ID
    );
    assertNotNull(response);
    assertTrue(response.getStatusCode().is4xxClientError());
  }
}

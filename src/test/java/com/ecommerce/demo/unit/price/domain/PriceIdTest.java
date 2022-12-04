package com.ecommerce.demo.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.demo.price.domain.PriceId;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;

class PriceIdTest {

  @Test
void createPriceId() {
    PriceId priceId = new PriceId(1L);
    assertNotNull(priceId);
    assertEquals(1L, priceId.value());
  }
  @Test
void comparePriceId() {
    PriceId priceId = new PriceId(1L);
    PriceId priceId2 = new PriceId(1L);
    assertEquals(priceId, priceId2);
    PriceId priceId3 = new PriceId(2L);
    assertNotEquals(priceId, priceId3);
    assertEquals(1, priceId3.compareTo(priceId2));
  }

  @Test
  void shouldFailWhenInvalidId() {
    assertThrows(InvalidIdException.class, () -> new PriceId(-1L));
    assertThrows(InvalidIdException.class, () -> new PriceId(0L));
    assertThrows(InvalidIdException.class, () -> new PriceId(null));
  }
}

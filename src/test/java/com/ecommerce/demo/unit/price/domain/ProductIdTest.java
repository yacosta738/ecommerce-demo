package com.ecommerce.demo.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.demo.price.domain.ProductId;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;

class ProductIdTest {

  @Test
void createProductId() {
    ProductId productId = new ProductId(1L);
    assertNotNull(productId);
    assertEquals(1L, productId.value());
  }
  @Test
void compareProductId() {
    ProductId productId = new ProductId(1L);
    ProductId productId2 = new ProductId(1L);
    assertEquals(productId, productId2);
    ProductId productId3 = new ProductId(2L);
    assertNotEquals(productId, productId3);
    assertEquals(1, productId3.compareTo(productId2));
  }

  @Test
  void shouldFailWhenInvalidId() {
    assertThrows(InvalidIdException.class, () -> new ProductId(-1L));
    assertThrows(InvalidIdException.class, () -> new ProductId(0L));
    assertThrows(InvalidIdException.class, () -> new ProductId(null));
  }
}

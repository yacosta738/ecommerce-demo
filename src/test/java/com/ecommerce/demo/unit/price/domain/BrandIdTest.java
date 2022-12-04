package com.ecommerce.demo.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.demo.price.domain.BrandId;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;

class BrandIdTest {

  @Test
void createBrandId() {
    BrandId brandId = new BrandId(1L);
    assertNotNull(brandId);
    assertEquals(1L, brandId.value());
  }
  @Test
void compareBrandId() {
    BrandId brandId = new BrandId(1L);
    BrandId brandId2 = new BrandId(1L);
    assertEquals(brandId, brandId2);
    BrandId brandId3 = new BrandId(2L);
    assertNotEquals(brandId, brandId3);
    assertEquals(1, brandId3.compareTo(brandId2));
  }

  @Test
  void shouldFailWhenInvalidId() {
    assertThrows(InvalidIdException.class, () -> new BrandId(-1L));
    assertThrows(InvalidIdException.class, () -> new BrandId(0L));
    assertThrows(InvalidIdException.class, () -> new BrandId(null));
  }
}

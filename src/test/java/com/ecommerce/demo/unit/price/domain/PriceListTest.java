package com.ecommerce.demo.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.demo.price.domain.PriceList;
import com.ecommerce.demo.price.domain.exceptions.InvalidListException;
import org.junit.jupiter.api.Test;

class PriceListTest {

  @Test
void createPriceList() {
    PriceList priceList = new PriceList(1L);
    assertNotNull(priceList);
    assertEquals(1L, priceList.value());
  }
  @Test
void comparePriceList() {
    PriceList priceList = new PriceList(1L);
    PriceList priceList2 = new PriceList(1L);
    assertEquals(priceList, priceList2);
    PriceList priceList3 = new PriceList(2L);
    assertNotEquals(priceList, priceList3);
    assertEquals(1, priceList3.compareTo(priceList2));
  }

  @Test
  void shouldFailWhenInvalidId() {
    assertThrows(InvalidListException.class, () -> new PriceList(-1L));
    assertThrows(InvalidListException.class, () -> new PriceList(0L));
    assertThrows(InvalidListException.class, () -> new PriceList(null));
  }
}

package com.ecommerce.demo.unit.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ecommerce.demo.common.domain.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

class MoneyTest {

  @Test
  void shouldCreateMoney() {
    Money money = new Money(BigDecimal.ONE);
    assertNotNull(money);
    assertEquals(BigDecimal.ONE, money.amount());
  }

  @Test
  void shouldThrowExceptionWhenCreateMoneyWithNullAmount() {
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> new Money(null));
    assertEquals("Amount cannot be null", illegalArgumentException.getMessage());
  }

  @Test
  void shouldReturnTrueWhenIsGreaterThanZero() {
    Money money = new Money(BigDecimal.ONE);
    assertTrue(money.isGreaterThanZero());
  }

  @Test
  void shouldReturnFalseWhenIsGreaterThanZero() {
    Money money = new Money(BigDecimal.ZERO);
    assertFalse(money.isGreaterThanZero());
  }

  @Test
  void shouldReturnTrueWhenIsGreaterThan() {
    Money money = new Money(BigDecimal.ONE);
    assertTrue(money.isGreaterThan(new Money(BigDecimal.ZERO)));
  }

  @Test
  void shouldReturnFalseWhenIsGreaterThan() {
    Money money = new Money(BigDecimal.ZERO);
    assertFalse(money.isGreaterThan(new Money(BigDecimal.ONE)));
  }

  @Test
  void shouldAddMoney() {
    Money money = new Money(BigDecimal.ONE);
    Money moneyToAdd = new Money(BigDecimal.ONE);
    Money result = money.add(moneyToAdd);
    assertEquals(BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_EVEN), result.amount());
  }

  @Test
  void shouldSubtractMoney() {
    Money money = new Money(BigDecimal.ONE);
    Money moneyToSubtract = new Money(BigDecimal.ONE);
    Money result = money.subtract(moneyToSubtract);
    assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN), result.amount());
  }

  @Test
  void shouldMultiplyMoney() {
    Money money = new Money(BigDecimal.ONE);
    Money result = money.multiply(2);
    assertEquals(BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_EVEN), result.amount());
  }

  @Test
  void shouldReturnTrueWhenEquals() {
    Money money = new Money(BigDecimal.ONE);
    assertEquals(money, new Money(BigDecimal.ONE));
  }

  @Test
  void shouldReturnFalseWhenEquals() {
    Money money = new Money(BigDecimal.ONE);
    assertNotEquals(money, new Money(BigDecimal.ZERO));
  }

}

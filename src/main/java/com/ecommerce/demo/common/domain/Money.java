package com.ecommerce.demo.common.domain;

import com.ecommerce.demo.common.domain.exceptions.InvalidMoneyException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Money value object.
 *
 * @author Yuniel Acosta
 * @param amount the amount of money.
 */
public record Money(BigDecimal amount) {

  public static final int SCALE = 2;
  public static final Money ZERO = new Money(BigDecimal.ZERO);

  public Money {
    validate(amount);
  }

  private void validate(BigDecimal amount) {
    if (amount == null) {
      throw new InvalidMoneyException("Amount cannot be null");
    }
  }

  public boolean isGreaterThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Money money) {
    return this.amount != null && this.amount.compareTo(money.amount()) > 0;
  }

  public Money add(Money money) {
    return new Money(scale(this.amount.add(money.amount())));
  }

  public Money subtract(Money money) {
    return new Money(scale(this.amount.subtract(money.amount())));
  }

  public Money multiply(int multiplier) {
    return new Money(scale(this.amount.multiply(new BigDecimal(multiplier))));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return amount.equals(money.amount);
  }

  private BigDecimal scale(BigDecimal input) {
    return input.setScale(SCALE, RoundingMode.HALF_EVEN);
  }
}
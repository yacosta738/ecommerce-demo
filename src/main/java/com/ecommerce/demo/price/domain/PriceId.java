package com.ecommerce.demo.price.domain;

import com.ecommerce.demo.common.domain.BaseValidateValueObject;
import com.ecommerce.demo.common.domain.BaseValueObject;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;

/**
 * Price id. This class represents the id of a price.
 * @author Yuniel Acosta
 */
public class PriceId extends BaseValidateValueObject<Long> {

  public PriceId(Long value) {
    super(value);
  }

  @Override
  public void validate(Long value) {
    if (value == null || value <= 0) {
      throw new InvalidIdException("Price id cannot be null or less than 1");
    }
  }

  @Override
  public int compareTo(BaseValueObject<Long> o) {
    return this.value().compareTo(o.value());
  }
}

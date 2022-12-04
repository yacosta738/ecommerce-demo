package com.ecommerce.demo.price.domain;

import com.ecommerce.demo.common.domain.BaseValidateValueObject;
import com.ecommerce.demo.common.domain.BaseValueObject;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;

public class ProductId extends BaseValidateValueObject<Long> {

  public ProductId(Long value) {
    super(value);
  }

  @Override
  public void validate(Long value) {
    if (value == null || value < 1) {
      throw new InvalidIdException("Product id cannot be null or less than 1");
    }
  }

  @Override
  public int compareTo(BaseValueObject<Long> o) {
    return this.value().compareTo(o.value());
  }
}

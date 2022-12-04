package com.ecommerce.demo.common.domain;

/**
 * Base validate value object. This class represents the base class for all value objects that need
 * validation.
 * @param <T> the type of the value object.
 */
public abstract class BaseValidateValueObject<T> extends BaseValueObject<T> {


  protected BaseValidateValueObject(T value) {
    super(value);
    validate(value);

  }

  /**
   * Validate the value object.
   * @param value the value object.
   */
  public abstract void validate(T value);
}

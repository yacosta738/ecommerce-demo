package com.ecommerce.demo.common.domain;

import java.util.Objects;
/**
 * Base value object. This class represents the base class for all value objects.
 * @param <T> the type of the value object.
 */
public abstract class BaseValueObject<T> implements Comparable<BaseValueObject<T>> {

  private final T value;

  protected BaseValueObject(T value) {
    this.value = value;
  }

  public T value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseValueObject<?> that)) {
      return false;
    }
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}

package com.ecommerce.demo.price.domain.exceptions;

import com.ecommerce.demo.exceptions.ApiException;
import java.io.Serial;

/**
 * Price validation exception. This exception is thrown when a price is not valid.
 *
 * @author Yuniel Acosta
 */
public abstract class ValidationException extends ApiException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected ValidationException(String message) {
    super("invalid_price", message, 400);
  }
}
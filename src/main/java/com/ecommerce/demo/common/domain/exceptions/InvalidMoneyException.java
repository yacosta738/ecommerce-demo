package com.ecommerce.demo.common.domain.exceptions;

import java.io.Serial;

/**
 * Exception thrown when a money value is invalid.
 *
 * @author Yuniel Acosta
 */
public class InvalidMoneyException extends IllegalArgumentException {
  @Serial
  private static final long serialVersionUID = 1L;
  public InvalidMoneyException(String message) {
    super(message);
  }
}
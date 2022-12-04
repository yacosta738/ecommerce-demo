package com.ecommerce.demo.price.domain.exceptions;
/**
 * Invalid price list exception. This exception is thrown when the price list is invalid.
 * @author Yuniel Acosta
 */
public class InvalidListException extends ValidationException {

  public InvalidListException(String message) {
    super(message);
  }
}

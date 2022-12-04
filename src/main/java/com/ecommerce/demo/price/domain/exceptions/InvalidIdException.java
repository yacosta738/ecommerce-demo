package com.ecommerce.demo.price.domain.exceptions;

/**
 * Invalid id exception. This exception is thrown when a price id is not valid.
 *
 * @author Yuniel Acosta
 */
public class InvalidIdException extends ValidationException {

  public InvalidIdException(String message) {
    super(message);
  }
}
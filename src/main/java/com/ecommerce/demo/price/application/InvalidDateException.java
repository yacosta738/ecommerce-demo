package com.ecommerce.demo.price.application;

import com.ecommerce.demo.exceptions.ApiException;

public class InvalidDateException extends ApiException {

  public InvalidDateException(String message) {
    super("invalid_date", message, 400);
  }
}


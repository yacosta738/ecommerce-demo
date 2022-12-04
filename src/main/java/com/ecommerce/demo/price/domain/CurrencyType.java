package com.ecommerce.demo.price.domain;
/**
 * Currency type. This class represents the currency type of a price.
 * @author Yuniel Acosta
 */
public enum CurrencyType {
  PEN("PEN"),
  USD("USD"),
  EUR("EUR"),
  GBP("GBP"),
  JPY("JPY");
  private final String type;
  CurrencyType(String currency) {
    this.type = currency;
  }
  public String value() {
    return type;
  }
}

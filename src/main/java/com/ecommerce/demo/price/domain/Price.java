package com.ecommerce.demo.price.domain;

import com.ecommerce.demo.common.domain.Money;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Price. This class represents a price of a product.
 *
 * @author Yuniel Acosta
 */
public class Price implements Comparable<Price> {

  private PriceId id;
  private PriceList priceList;
  private BrandId brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private ProductId productId;
  private int priority;
  private Money priceMoney;
  private CurrencyType currencyType;

  public PriceId getId() {
    return id;
  }

  public PriceList getPriceList() {
    return priceList;
  }

  public BrandId getBrandId() {
    return brandId;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public ProductId getProductId() {
    return productId;
  }

  public int getPriority() {
    return priority;
  }

  public Money getPrice() {
    return priceMoney;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Price price)) {
      return false;
    }
    return priority == price.priority && id.equals(price.id) && priceList.equals(price.priceList)
        && brandId.equals(price.brandId) && startDate.equals(price.startDate) && endDate.equals(
        price.endDate) && productId.equals(price.productId) && priceMoney.equals(price.priceMoney)
        && currencyType == price.currencyType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, priceList, brandId, startDate, endDate, productId, priority, priceMoney,
        currencyType);
  }

  @Override
  public int compareTo(Price o) {

    if (this.priceMoney.amount().compareTo(o.priceMoney.amount()) == 0
        && this.currencyType == o.currencyType) {
      return Integer.compare(this.priority, o.priority);
    } else if (this.brandId.equals(o.brandId) && this.productId.equals(o.productId)
        && this.startDate.equals(o.startDate) && this.endDate.equals(o.endDate)
        && this.priceList.equals(o.priceList) && this.priority == o.priority) {
      return 0;
    } else {
      return -1;
    }
  }

  public static final class PriceBuilder {

    private Long id;
    private Long priceList;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long productId;
    private int priority;
    private BigDecimal priceMoney;
    private CurrencyType currencyType;

    private PriceBuilder() {
    }

    public PriceBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public PriceBuilder priceList(Long priceList) {
      this.priceList = priceList;
      return this;
    }

    public PriceBuilder brandId(Long brandId) {
      this.brandId = brandId;
      return this;
    }

    public PriceBuilder startDate(LocalDateTime startDate) {
      this.startDate = startDate;
      return this;
    }

    public PriceBuilder endDate(LocalDateTime endDate) {
      this.endDate = endDate;
      return this;
    }

    public PriceBuilder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public PriceBuilder priority(int priority) {
      this.priority = priority;
      return this;
    }

    public PriceBuilder price(BigDecimal priceMoney) {
      this.priceMoney = priceMoney;
      return this;
    }

    public PriceBuilder currencyType(CurrencyType currencyType) {
      this.currencyType = currencyType;
      return this;
    }

    public Price build() {
      Price price = new Price();
      price.productId = new ProductId(productId);
      price.startDate = this.startDate;
      price.priceMoney = new Money(this.priceMoney);
      price.id = new PriceId(id);
      price.endDate = this.endDate;
      price.priceList = new PriceList(priceList);
      price.brandId = new BrandId(brandId);
      price.currencyType = this.currencyType;
      price.priority = this.priority;
      return price;
    }
  }

  public static PriceBuilder builder() {
    return new PriceBuilder();
  }
}

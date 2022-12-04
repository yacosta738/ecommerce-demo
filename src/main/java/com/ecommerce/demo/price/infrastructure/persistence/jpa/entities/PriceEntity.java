package com.ecommerce.demo.price.infrastructure.persistence.jpa.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Price entity. This class represents a price entity.
 *
 * @author Yuniel Acosta
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Nonnull
  @Column(name = "PRICE_LIST")
  private Long priceList;

  @Nonnull
  @Column(name = "BRAND_ID")
  private Long brandId;
  @Nonnull
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Nonnull
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Nonnull
  @Column(name = "PRODUCT_ID")
  private Long productId;
  @Nonnull
  @Column(name = "PRIORITY")
  private Integer priority;
  @Nonnull
  @Column(name = "PRICE")
  private BigDecimal price;
  @Nonnull
  @Column(name = "CURR")
  private String currencyType;
}

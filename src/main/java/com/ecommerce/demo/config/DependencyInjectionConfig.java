package com.ecommerce.demo.config;

import com.ecommerce.demo.price.application.PriceFinder;
import com.ecommerce.demo.price.domain.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

  @Bean
  public PriceFinder priceFinder(PriceRepository priceRepository) {
    return new PriceFinder(priceRepository);
  }
}

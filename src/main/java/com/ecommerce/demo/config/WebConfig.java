package com.ecommerce.demo.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

/**
 * Web configuration.
 *
 * @author Yuniel Acosta
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Locale resolver. This is used to set the default locale.
   *
   * @return LocaleResolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    return new FixedLocaleResolver(Locale.US);
  }

}

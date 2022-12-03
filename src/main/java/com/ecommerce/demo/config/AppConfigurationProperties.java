package com.ecommerce.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
@ConfigurationPropertiesScan
public class AppConfigurationProperties {

  private String title;
  private String description;
  private String version;
}
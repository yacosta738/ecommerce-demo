package com.ecommerce.demo;

import com.ecommerce.demo.config.AppConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main class. This is the entry point of the application.
 *
 * @author Yuniel Acosta
 */
@SpringBootApplication
@EnableConfigurationProperties(AppConfigurationProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

package com.ecommerce.demo.price.infrastructure.rest;

import com.ecommerce.demo.price.application.InvalidDateException;
import com.ecommerce.demo.price.domain.exceptions.InvalidIdException;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceRequest;
import com.ecommerce.demo.price.infrastructure.rest.dto.PriceResponse;
import com.ecommerce.demo.price.infrastructure.service.PriceService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Price controller. This controller is responsible for exposing the price API.
 *
 * @author Yuniel Acosta
 */
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = "application/vnd.api.v1+json")
public class PriceController {

  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  /**
   * GET /api/price?applicationDate=2020-05-10T11:15:30&productId=35455&brandId=1
   * Finds the price of a product.
   *
   * @param applicationDate the application date.
   * @param productId the product id.
   * @param brandId the brand id.
   * @return the price of the product. If there is no price for the product, it returns 404
   * @throws InvalidDateException if the application date is null.
   * @throws InvalidIdException   if the product id or brand id is null.
   */
  @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PriceResponse> findPrice(
      @RequestParam(name = "applicationDate") String applicationDate,
      @RequestParam(name = "productId") Long productId,
      @RequestParam(name = "brandId") Long brandId) {
    log.info("Request received: applicationDate={}, productId={}, brandId={}", applicationDate,
        productId, brandId);
    Optional<PriceResponse> optionalPriceResponse = priceService.findPrice(PriceRequest.builder()
        .applicationDate(LocalDateTime.parse(applicationDate))
        .productId(productId)
        .brandId(brandId)
        .build());

    return optionalPriceResponse.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}

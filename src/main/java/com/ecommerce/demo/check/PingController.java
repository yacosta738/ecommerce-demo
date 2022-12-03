package com.ecommerce.demo.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller responsible for /ping implementation.
 *
 * @author Yuniel Acosta
 */
@RestController
@Slf4j
public class PingController {

  /**
   * @return "pong" String.
   */
  @GetMapping("/ping")
  public String ping() {
    log.debug("ping");
    return "pong";
  }
}

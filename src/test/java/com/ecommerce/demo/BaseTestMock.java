package com.ecommerce.demo;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.mockito.MockedStatic;

public class BaseTestMock {
  private MockedStatic<LocalDateTime> localDateTimeMockedStatic;
  protected void givenFixedDate(LocalDateTime fixedDate) {
    localDateTimeMockedStatic = mockStatic(LocalDateTime.class, CALLS_REAL_METHODS);
    localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(fixedDate);
  }

  @AfterEach
  protected void cleanMocks() {
    if (localDateTimeMockedStatic != null) {
      localDateTimeMockedStatic.close();
    }
  }
}

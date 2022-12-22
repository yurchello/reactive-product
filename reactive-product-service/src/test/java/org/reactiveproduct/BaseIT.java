package org.reactiveproduct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class BaseIT {

  protected WebTestClient webTestClient;

  @LocalServerPort
  protected int randomServerPort;

  @BeforeEach
  public void setup() {
    this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
  }
}

package org.reactiveproduct.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.reactiveproduct.BaseIT;
import org.reactiveproduct.dto.ProductDto;


public class ProductControllerIT extends BaseIT {

  private static final String URI = "/product";

  @Test
  public void shouldReturnById() {
    // When
    webTestClient.get()
        .uri(URI + "/" + 1L)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ProductDto.class)
        .value(productDto -> {
          assertThat(productDto).isNotNull();
          assertThat(productDto.getId()).isEqualTo(1L);
        });
  }

  @Test
  public void shouldReturnAll() {
    // When
    webTestClient.get()
        .uri(URI + "/list")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ProductDto.class)
        .value(productDtos -> {
          assertThat(productDtos).isNotEmpty();
        });
  }

  @Test
  public void shouldCreate() {
    //given
    ProductDto prod = new ProductDto(null, "e", "r");
    // When
    webTestClient.post()
        .uri(URI)
        .bodyValue(prod)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ProductDto.class)
        .value(productDto -> {
          assertThat(productDto).isNotNull();
          assertThat(productDto.getId()).isNotNull();
          assertThat(productDto.getTitle()).isEqualTo(prod.getTitle());
        });
  }

  @Test
  public void shouldHandleException() {
    // When
    webTestClient.get()
        .uri(URI + "/test-client/exception")
        .exchange()
        .expectStatus().is5xxServerError();
  }
}
package org.reactiveproduct.client;

import org.reactiveproduct.dto.ProductDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

  private static final String BASE_URL = "http://localhost:8081/product";

  public Mono<ProductDto> getById(Long id) {
    return WebClient.create().get().uri(BASE_URL + "/" + id).retrieve().bodyToMono(ProductDto.class);
  }

  public Mono<ProductDto> create(ProductDto productDto) {
    return WebClient.create().post().uri(BASE_URL).contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(productDto), new ParameterizedTypeReference<ProductDto>() {
        }).retrieve().bodyToMono(ProductDto.class);
  }

  public Mono<ProductDto> testException() {
    return WebClient.create().get().uri(BASE_URL + "/test-client/exception").retrieve().bodyToMono(ProductDto.class);
  }
}

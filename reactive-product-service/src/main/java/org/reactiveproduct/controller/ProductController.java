package org.reactiveproduct.controller;

import lombok.AllArgsConstructor;
import org.reactiveproduct.dto.ProductDto;
import org.reactiveproduct.service.ProductClientService;
import org.reactiveproduct.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

  private ProductService productService;
  private ProductClientService productClientService;

  @GetMapping("/{id}")
  public Mono<ProductDto> getProduct(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  @PostMapping
  public Mono<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }

  @GetMapping("/list")
  public Flux<ProductDto> getAll() {
    return productService.findAll();
  }

  @PutMapping("/tr-op")
  public Mono<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
    return productService.updateWithOperator(productDto);
  }

  /**
   * Just for testing the web client within Spring context
   *
   * @return
   */
  @GetMapping("/test-client")
  public Mono<String> testClient() {
    productClientService.testClient();
    return Mono.just("OK");
  }

  @GetMapping("/test-client/exception")
  public Mono<ProductDto> testException() {
    return productService.exceptionTest();
  }
}

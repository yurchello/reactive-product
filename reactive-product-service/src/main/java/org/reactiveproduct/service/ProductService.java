package org.reactiveproduct.service;

import org.reactiveproduct.dto.ProductDto;
import org.reactiveproduct.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductDto> getProduct(Long id);

  Mono<ProductDto> createProduct(ProductDto productDto);

  Flux<ProductDto> findAll();

  Mono<ProductDto> updateWithOperator(ProductDto productDto);

  Mono<ProductDto> exceptionTest();

}

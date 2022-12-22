package org.reactiveproduct.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactiveproduct.client.ProductClient;
import org.reactiveproduct.dto.ProductDto;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class ProductClientService {
  private final ProductClient productClient;

  public void testClient(){

    productClient.create(new ProductDto(null, "created", "testGroup"))
        .doFinally(signalType-> log.info("doFinally create: {}", signalType))
        .subscribe(productDto -> log.info("subscribe create: {}", productDto));

    productClient.getById(1L)
        .subscribe(productDto -> log.info("subscribe getById: {}", productDto));

    productClient.testException()
        .doOnError(thr-> log.info("doOnError testException: {}", thr.getMessage()))
        .doFinally(signalType-> log.info("doFinally testException: {}", signalType))
        .subscribe(productDto -> log.info("subscribe testException: {}", productDto));
  }
}

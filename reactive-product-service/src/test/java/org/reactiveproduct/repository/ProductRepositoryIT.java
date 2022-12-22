package org.reactiveproduct.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.reactiveproduct.BaseIT;
import org.reactiveproduct.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductRepositoryIT extends BaseIT {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void findById() {
    //when
    Mono<Product> productMono = productRepository.findById(1L);
    //then
    StepVerifier.create(productMono)
        .expectNextMatches(p -> {
          return p.getId().equals(1L);
        })
        .expectComplete()
        .verify();
  }

  @Test
  void shouldFindAllByTitle() {
    //given
    productRepository.save(new Product(null, "Test Description", "")).block();
    productRepository.save(new Product(null, "Test Description", "")).block();
    //when
    Product product = productRepository.findAllByTitle("Test Description").blockFirst();
    //then
    assertThat(product).isNotNull();
    assertThat(product.getTitle()).isEqualTo("Test Description");

  }

  @Test
  void shouldFindAllByGroup() {
    //given
    productRepository.save(new Product(null, "", "group")).block();
    productRepository.save(new Product(null, "", "group")).block();
    //when
    List<Product> products = productRepository.findByGroup("group").collectList().block();
    //then
    assertThat(products).allMatch(p -> p.getGroup().equals("group"));
  }
}
package org.reactiveproduct.repository;

import org.reactiveproduct.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long>{

  Flux<Product> findAllByTitle(String title);

  @Query("select p.* from reactive_product.product p where p.pr_group = :prGroup")
  Flux<Product> findByGroup(@Param("prGroup")String prGroup);
}

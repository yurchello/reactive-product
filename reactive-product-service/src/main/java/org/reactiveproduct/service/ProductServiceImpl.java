package org.reactiveproduct.service;

import lombok.AllArgsConstructor;
import org.reactiveproduct.dto.ProductDto;
import org.reactiveproduct.entity.Product;
import org.reactiveproduct.mapper.ProductMapper;
import org.reactiveproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements  ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final TransactionalOperator transactionalOperator;

  public ProductServiceImpl(ReactiveTransactionManager transactionManager,
      ProductRepository productRepository,
      ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.transactionalOperator = TransactionalOperator.create(transactionManager);
  }

  @Override
  public Mono<ProductDto> getProduct(Long id) {
    Mono<Product> product = productRepository.findById(id);
    return product.map(pr->productMapper.toDto(pr));
  }

  @Override
  public Mono<ProductDto> createProduct(ProductDto productDto) {
    Mono<Product> product = productRepository.save(productMapper.toEntity(productDto));
    return product.map(pr-> productMapper.toDto(pr));
  }

  @Override
  public Flux<ProductDto> findAll() {
    return productRepository.findAll().map(product -> productMapper.toDto(product));
  }

  @Override
  public Mono<ProductDto> updateWithOperator(ProductDto productDto) {
    Mono<Product> productMonoSave1 = productRepository.save(new Product(productDto.getId(), productDto.getTitle(), "iii"));
    Mono<Product> productMonoSave2 = productRepository.save(new Product(productDto.getId(), productDto.getTitle(), "newTitle"));
    return productRepository.findById(productDto.getId())
        .then(productMonoSave1)
        .then(productMonoSave2)
        .map(productMapper::toDto)
        .as(transactionalOperator::transactional);
  }

  @Override
  public Mono<ProductDto> exceptionTest() {
    throw new RuntimeException("test exception");
  }
}

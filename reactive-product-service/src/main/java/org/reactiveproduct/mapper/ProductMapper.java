package org.reactiveproduct.mapper;

import org.mapstruct.Mapper;

import org.reactiveproduct.dto.ProductDto;
import org.reactiveproduct.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  Product toEntity(ProductDto dto);

  ProductDto toDto(Product product);
}

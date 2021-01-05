package com.alexme951.parseinetstores.mapper.converters;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import com.alexme951.parseinetstores.service.dto.Product;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDtoConverter extends
    BidirectionalConverter<Product, ProductParsingDto> {

  @Override
  public ProductParsingDto convertTo(Product source, Type<ProductParsingDto> destinationType,
      MappingContext mappingContext) {
    return null;
  }

  @Override
  public Product convertFrom(ProductParsingDto source, Type<Product> destinationType,
      MappingContext mappingContext) {
    return null;
  }
}

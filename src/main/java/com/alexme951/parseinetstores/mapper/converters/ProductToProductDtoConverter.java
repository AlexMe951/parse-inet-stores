package com.alexme951.parseinetstores.mapper.converters;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import com.alexme951.parseinetstores.service.dto.Product;
import com.google.gson.Gson;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductToProductDtoConverter extends
    BidirectionalConverter<Product, ProductParsingDto> {

  private final Gson gson;

  @Override
  public ProductParsingDto convertTo(Product source,
      Type<ProductParsingDto> destinationType,
      MappingContext mappingContext) {
    ProductParsingDto result = new ProductParsingDto();
    result.setName(source.name());
    result.setDescription(source.description());
    result.setCode(source.code());
    String attributes = gson.toJson(source.attributes());
    result.setAttributes(attributes);
    return result;
  }

  @Override
  public Product convertFrom(ProductParsingDto source, Type<Product> destinationType,
      MappingContext mappingContext) {

    Map<Object, Object> parsedMap = gson.fromJson(source.getAttributes(), Map.class);

    Map<String, String> attributes = parsedMap
        .entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                entry -> entry.getKey().toString(),
                entry -> entry.getValue().toString(),
                (first, second) -> first));

    return new Product(
        source.getName(),
        source.getDescription(),
        source.getCode(),
        attributes
    );
  }
}

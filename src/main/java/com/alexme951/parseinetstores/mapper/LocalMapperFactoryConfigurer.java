package com.alexme951.parseinetstores.mapper;

import com.alexme951.parseinetstores.mapper.converters.CategoryLinkToFromDtoConverter;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LocalMapperFactoryConfigurer implements OrikaMapperFactoryConfigurer {

  private final CategoryLinkToFromDtoConverter categoryLinkConverter;

  @Override
  public void configure(MapperFactory mapperFactory) {
    ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    converterFactory.registerConverter(categoryLinkConverter);
  }
}

package com.alexme951.parseinetstores.mapper.converters;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ParsingHistoryEntryDto;
import com.alexme951.parseinetstores.service.dto.CategoryLink;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class CategoryLinkToFromDtoConverter extends
    BidirectionalConverter<CategoryLink, CategoryLinkParsingDto> {

  @Override
  public CategoryLinkParsingDto convertTo(CategoryLink source,
      Type<CategoryLinkParsingDto> destinationType, MappingContext mappingContext) {
    ParsingHistoryEntryDto parsingHistoryEntryDto = new ParsingHistoryEntryDto(
        source.parsingTime()
    );
    return new CategoryLinkParsingDto(
        source.linkUrl(),
        parsingHistoryEntryDto,
        null
    );
  }

  @Override
  public CategoryLink convertFrom(CategoryLinkParsingDto source,
      Type<CategoryLink> destinationType,
      MappingContext mappingContext) {
    return new CategoryLink(
        source.getLinkUrl(),
        source.getParsingHistoryEntry().getParsingTime(),
        source.getParentCategoryLink().getLinkUrl()
    );
  }
}

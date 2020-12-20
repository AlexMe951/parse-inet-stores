package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.service.db.SaveProductLinkService;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveProductLinkServiceImpl implements SaveProductLinkService {

  private final MapperFacade mapperFacade;

  @Override
  public void saveProductLinks(List<ProductLink> productLinks) {

  }

  @Override
  public void saveProductLinks(List<ProductLink> productLinks, OffsetDateTime parsingTime) {

  }

  @Override
  public void saveProductLink(ProductLink productLink, OffsetDateTime parsingTime) {

  }
}

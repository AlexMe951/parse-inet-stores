package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.service.db.SaveCategoryLinkService;
import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveCategoryLinkServiceImpl implements SaveCategoryLinkService {

  private final MapperFacade mapperFacade;

  @Override
  public void saveCategoryLinks(List<CategoryLink> categoryLinks) {

  }

  @Override
  public void saveCategoryLinks(List<CategoryLink> categoryLinks, OffsetDateTime parsingTime) {

  }

  @Override
  public void saveCategoryLink(CategoryLink categoryLink, OffsetDateTime parsingTime) {
  }
}

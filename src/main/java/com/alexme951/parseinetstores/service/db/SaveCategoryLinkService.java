package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.time.OffsetDateTime;
import java.util.List;

public interface SaveCategoryLinkService {

  void saveCategoryLinks(List<CategoryLink> categoryLinks);

  void saveCategoryLinks(List<CategoryLink> categoryLinks, OffsetDateTime parsingTime);

  void saveCategoryLink(CategoryLink categoryLink, OffsetDateTime parsingTime);
}

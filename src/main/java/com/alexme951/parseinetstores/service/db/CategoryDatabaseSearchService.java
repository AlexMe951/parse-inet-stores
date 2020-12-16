package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import java.util.List;

public interface CategoryDatabaseSearchService {

  List<CategoryLinkParsingDto> readAllCategoryLinks();
  List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category);

  List<CategoryLinkParsingDto> readCategoryLinkById(Long id);
}

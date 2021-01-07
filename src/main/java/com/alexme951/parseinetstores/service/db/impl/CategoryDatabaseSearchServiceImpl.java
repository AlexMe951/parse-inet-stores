package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.service.db.CategoryDatabaseSearchService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryDatabaseSearchServiceImpl implements CategoryDatabaseSearchService {

  @Override
  public List<CategoryLinkParsingDto> readAllCategoryLinks() {
    return null;
  }

  @Override
  public List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category) {
    return null;
  }

  @Override
  public List<CategoryLinkParsingDto> readCategoryLinkById(Long id) {
    return null;
  }
}

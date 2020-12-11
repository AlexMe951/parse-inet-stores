package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryParsingDto;
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
  public List<CategoryParsingDto> readAllCategories() {
    return null;
  }

  @Override
  public List<CategoryParsingDto> readSubCategoriesByCategory(String category) {
    return null;
  }

  @Override
  public CategoryParsingDto readCategoryByName(String categoryName) {
    return null;
  }

  @Override
  public CategoryParsingDto readCategoryById(Long categoryId) {
    return null;
  }
}

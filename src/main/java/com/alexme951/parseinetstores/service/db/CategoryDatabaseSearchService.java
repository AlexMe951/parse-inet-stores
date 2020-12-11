package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryParsingDto;
import java.util.List;

public interface CategoryDatabaseSearchService {

  List<CategoryLinkParsingDto> readAllCategoryLinks();

  List<CategoryLinkParsingDto> readSubCategoryLinksByCategory(String category);

  List<CategoryParsingDto> readAllCategories();

  List<CategoryParsingDto> readSubCategoriesByCategory(String category);

  CategoryParsingDto readCategoryByName(String categoryName);

  CategoryParsingDto readCategoryById(Long categoryId);
}

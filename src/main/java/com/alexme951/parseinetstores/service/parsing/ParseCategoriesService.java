package com.alexme951.parseinetstores.service.parsing;

import com.alexme951.parseinetstores.service.dto.Category;
import java.util.List;

public interface ParseCategoriesService {

  List<Category> parseAllCategories();

  List<Category> parseSubCategoriesByCategory(String category);
}

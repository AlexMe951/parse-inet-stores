package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.service.dto.Category;
import com.alexme951.parseinetstores.service.parsing.ParseCategoriesService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParseCategoriesServiceImpl implements ParseCategoriesService {

  @Override
  public List<Category> parseAllCategories() {
    return null;
  }

  @Override
  public List<Category> parseSubCategoriesByCategory(String category) {
    return null;
  }
}

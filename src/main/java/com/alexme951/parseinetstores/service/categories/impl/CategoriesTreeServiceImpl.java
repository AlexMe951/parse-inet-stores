package com.alexme951.parseinetstores.service.categories.impl;

import com.alexme951.parseinetstores.service.categories.CategoriesTreeService;
import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.util.List;
import java.util.Optional;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CategoriesTreeServiceImpl implements CategoriesTreeService {

  @Override
  public Optional<CategoryLink> getParentCategory(CategoryLink link, Elements elements) {
    return Optional.empty();
  }

  @Override
  public List<CategoryLink> getAllParentCategories(CategoryLink link, Elements elements) {
    return null;
  }

  @Override
  public List<CategoryLink> getAllSubCategories(CategoryLink link, Elements elements) {
    return null;
  }

  @Override
  public List<CategoryLink> getAllDirectSubCategories(CategoryLink link, Elements elements) {
    return null;
  }
}

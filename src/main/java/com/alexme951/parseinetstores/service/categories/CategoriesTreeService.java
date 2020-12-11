package com.alexme951.parseinetstores.service.categories;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.util.List;
import java.util.Optional;
import org.jsoup.select.Elements;

public interface CategoriesTreeService {
  Optional<CategoryLink> getParentCategory(CategoryLink link, Elements elements);
  List<CategoryLink> getAllParentCategories(CategoryLink link, Elements elements);
  List<CategoryLink> getAllSubCategories(CategoryLink link, Elements elements);
  List<CategoryLink> getAllDirectSubCategories(CategoryLink link, Elements elements);
}

package com.alexme951.parseinetstores.service.parsing;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.util.List;

public interface ParseCategoryLinksService {

  List<CategoryLink> parseAllCatalogLinks();

  List<CategoryLink> parseSubCategoryLinks(String category);
}

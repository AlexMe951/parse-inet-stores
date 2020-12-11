package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import com.alexme951.parseinetstores.service.parsing.ParseCategoryLinksService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParseCategoryLinksServiceImpl implements ParseCategoryLinksService {

  @Override
  public List<CategoryLink> parseAllCatalogLinks() {
    return null;
  }

  @Override
  public List<CategoryLink> parseSubCategoryLinks(String category) {
    return null;
  }
}

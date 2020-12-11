package com.alexme951.parseinetstores.service.jsoup.impl;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.ExtractUrlsService;
import java.util.Set;
import javax.lang.model.util.Elements;
import org.springframework.stereotype.Service;

@Service
public class ExtractUrlsServiceImpl implements ExtractUrlsService {

  @Override
  public Set<ProductLink> extractProductUrls(Elements elements) {
    return null;
  }

  @Override
  public Set<CategoryLink> extractCategoryUrls(Elements elements) {
    return null;
  }
}

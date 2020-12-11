package com.alexme951.parseinetstores.service.jsoup;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import java.util.Set;
import javax.lang.model.util.Elements;

public interface ExtractUrlsService {
  Set<ProductLink> extractProductUrls(Elements elements);
  Set<CategoryLink> extractCategoryUrls(Elements elements);
}

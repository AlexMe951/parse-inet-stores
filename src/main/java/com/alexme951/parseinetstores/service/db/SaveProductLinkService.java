package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.service.dto.ProductLink;
import java.time.OffsetDateTime;
import java.util.List;

public interface SaveProductLinkService {

  void saveProductLinks(List<ProductLink> productLinks);

  void saveProductLinks(List<ProductLink> productLinks, OffsetDateTime parsingTime);

  void saveProductLink(ProductLink productLink, OffsetDateTime parsingTime);

}

package com.alexme951.parseinetstores.service.parsing;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;

/**
 * Service to extract info from product's page
 */
public interface ExtractProductInfoService {

  /**
   * Parses info from product's page
   *
   * @param productLink Product link
   * @return Product DTO
   */
  Product extractInfo(ProductLink productLink);
}

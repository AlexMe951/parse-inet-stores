package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import com.alexme951.parseinetstores.service.db.ProductDatabaseSearchService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductDatabaseSearchServiceImpl implements ProductDatabaseSearchService {

  @Override
  public List<ProductLinkParsingDto> readAllProductLinks() {
    return null;
  }

  @Override
  public List<ProductLinkParsingDto> readProductLinksByCategory(String category) {
    return null;
  }

  @Override
  public List<ProductParsingDto> readAllProducts() {
    return null;
  }

  @Override
  public List<ProductParsingDto> readProductsByCategory(String category) {
    return null;
  }

  @Override
  public ProductParsingDto readProductByName(String productName) {
    return null;
  }

  @Override
  public ProductParsingDto readProductById(Long productId) {
    return null;
  }
}

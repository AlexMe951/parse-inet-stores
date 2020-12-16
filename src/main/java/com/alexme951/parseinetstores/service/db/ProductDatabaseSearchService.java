package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import java.util.List;

public interface ProductDatabaseSearchService {

  List<ProductLinkParsingDto> readAllProductLinks();

  List<ProductLinkParsingDto> readProductLinksByCategory(String category);

  List<ProductParsingDto> readAllProducts();

  List<ProductParsingDto> readProductsByCategory(String category);

  ProductParsingDto readProductByName(String productName);

  ProductParsingDto readProductById(Long productId);
}

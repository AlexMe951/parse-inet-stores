package com.alexme951.parseinetstores.service.parsing;

import com.alexme951.parseinetstores.service.dto.Product;
import java.util.List;

public interface ParseProductsService {

  List<Product> parseAllProducts();

  List<Product> parseProductsByCategory(String category);
}

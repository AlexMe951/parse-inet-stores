package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.service.dto.Product;
import java.time.OffsetDateTime;
import java.util.List;

public interface SaveProductService {

  void saveProducts(List<Product> products);

  void saveProducts(List<Product> products, OffsetDateTime parsingTime);

  void saveProduct(Product product, OffsetDateTime parsingTime);

}

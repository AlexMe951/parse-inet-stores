package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.parsing.ParseProductsService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParseProductsServiceImpl implements ParseProductsService {

  @Override
  public List<Product> parseAllProducts() {
    return null;
  }

  @Override
  public List<Product> parseProductsByCategory(String category) {
    return null;
  }
}

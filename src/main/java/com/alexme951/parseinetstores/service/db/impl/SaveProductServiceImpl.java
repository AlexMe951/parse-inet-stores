package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.service.db.SaveProductService;
import com.alexme951.parseinetstores.service.dto.Product;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveProductServiceImpl implements SaveProductService {

  private final MapperFacade mapperFacade;

  @Override
  public void saveProducts(List<Product> products) {

  }

  @Override
  public void saveProducts(List<Product> products, OffsetDateTime parsingTime) {

  }

  @Override
  public void saveProduct(Product product, OffsetDateTime parsingTime) {

  }
}

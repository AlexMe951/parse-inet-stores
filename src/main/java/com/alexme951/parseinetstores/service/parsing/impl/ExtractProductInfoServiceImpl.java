package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import com.alexme951.parseinetstores.service.parsing.ExtractProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtractProductInfoServiceImpl implements ExtractProductInfoService {

  private final JsoupFacadeService jsoup;

  @Override
  public Product extractInfo(ProductLink productLink) {
    return null;
  }
}

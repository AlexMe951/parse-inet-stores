package com.alexme951.parseinetstores.service.parsing;

import com.alexme951.parseinetstores.service.dto.ProductLink;
import java.util.List;

public interface ParseProductLinksService {

  List<ProductLink> parseAllProductLinks();

  List<ProductLink> parseProductLinks(String category);
}

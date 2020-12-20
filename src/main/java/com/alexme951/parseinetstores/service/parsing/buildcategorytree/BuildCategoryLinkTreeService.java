package com.alexme951.parseinetstores.service.parsing.buildcategorytree;

import com.alexme951.parseinetstores.service.dto.CategoryLink;
import java.util.List;

public interface BuildCategoryLinkTreeService {

  List<CategoryLink> buildCategoryLinkTree(List<String> categoryLinkUrls);

  List<CategoryLink> buildCategoryLinkTreeForCategory(String category, List<String> categoryLinkUrls);
}

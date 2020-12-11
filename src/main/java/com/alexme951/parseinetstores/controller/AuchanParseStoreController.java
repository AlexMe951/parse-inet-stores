package com.alexme951.parseinetstores.controller;

import com.alexme951.parseinetstores.service.dto.Category;
import com.alexme951.parseinetstores.service.dto.CategoryLink;
import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.parsing.ParseCategoriesService;
import com.alexme951.parseinetstores.service.parsing.ParseCategoryLinksService;
import com.alexme951.parseinetstores.service.parsing.ParseProductLinksService;
import com.alexme951.parseinetstores.service.parsing.ParseProductsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/auchan/")
@RequiredArgsConstructor
public class AuchanParseStoreController {

  private final ParseProductLinksService parseProductLinksService;
  private final ParseProductsService parseProductsService;
  private final ParseCategoryLinksService parseCategoryLinksService;
  private final ParseCategoriesService parseCategoriesService;

  @PostMapping("/parse/productlinks")
  public ResponseEntity<List<ProductLink>> parseAllProductLinks(){
    List<ProductLink> result = parseProductLinksService.parseAllProductLinks();
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/productlinks/{category}")
  public ResponseEntity<List<ProductLink>> parseProductLinksByCategory(@PathVariable String category) {
    List<ProductLink> result = parseProductLinksService.parseProductLinks(category);
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/products")
  public ResponseEntity<List<Product>> parseAllProducts(){
    List<Product> result = parseProductsService.parseAllProducts();
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/products/{category}")
  public ResponseEntity<List<Product>> parseProductsByCategory(@PathVariable String category){
    List<Product> result = parseProductsService.parseProductsByCategory(category);
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/categorylinks")
  public ResponseEntity<List<CategoryLink>> parseAllCategoryLinks(){
    List<CategoryLink> result = parseCategoryLinksService.parseAllCatalogLinks();
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/categorylinks/{category}")
  public ResponseEntity<List<CategoryLink>> parseSubCategoryLinksByCategory(
      @PathVariable("category") String category){
    List<CategoryLink> result = parseCategoryLinksService.parseSubCategoryLinks(category);
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/categories")
  public ResponseEntity<List<Category>> parseAllCategories(){
    List<Category> result = parseCategoriesService.parseAllCategories();
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/categories/{category}")
  public ResponseEntity<List<Category>> parseSubcategoriesByCategory(@PathVariable String category){
    List<Category> result = parseCategoriesService.parseSubCategoriesByCategory(category);
    return ResponseEntity.ok().body(result);
  }
}

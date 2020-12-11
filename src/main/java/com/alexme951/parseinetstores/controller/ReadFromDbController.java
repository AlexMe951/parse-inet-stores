package com.alexme951.parseinetstores.controller;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ProductLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import com.alexme951.parseinetstores.service.db.CategoryDatabaseSearchService;
import com.alexme951.parseinetstores.service.db.ProductDatabaseSearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/readfromdb/")
@RequiredArgsConstructor
public class ReadFromDbController {

  private final CategoryDatabaseSearchService categoryDatabaseSearchService;
  private final ProductDatabaseSearchService productDatabaseSearchService;

  @GetMapping("/categorylinks/")
  public ResponseEntity<List<CategoryLinkParsingDto>> readAllCategoryLinks(){
    List<CategoryLinkParsingDto> result = categoryDatabaseSearchService.readAllCategoryLinks();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/categorylinks/category")
  public ResponseEntity<List<CategoryLinkParsingDto>> readSubCategoryLinksByCategory(@PathVariable String category){
    List<CategoryLinkParsingDto> result = categoryDatabaseSearchService.readSubCategoryLinksByCategory(category);
    return ResponseEntity.ok().body(result);
  }
  
  @GetMapping("/categories/")
  public ResponseEntity<List<CategoryParsingDto>> readAllCategories(){
    List<CategoryParsingDto> result = categoryDatabaseSearchService.readAllCategories();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/categories/{category}")
  public ResponseEntity<List<CategoryParsingDto>> readSubCategoriesByCategory(@PathVariable String category){
    List<CategoryParsingDto> result = categoryDatabaseSearchService.readSubCategoriesByCategory(category);
    return ResponseEntity.ok().body(result);
  }
  
  @GetMapping("/category/{categoryName}")
  public ResponseEntity<CategoryParsingDto> readCategoryByName(@PathVariable String categoryName){
    CategoryParsingDto result = categoryDatabaseSearchService.readCategoryByName(categoryName);
    return ResponseEntity.ok().body(result);
  }
  
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<CategoryParsingDto> readCategoryById(@PathVariable Long categoryId){
    CategoryParsingDto result = categoryDatabaseSearchService.readCategoryById(categoryId);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/productlinks/")
  public ResponseEntity<List<ProductLinkParsingDto>> readAllProductLinks(){
    List<ProductLinkParsingDto> result = productDatabaseSearchService.readAllProductLinks();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/productlinks/category")
  public ResponseEntity<List<ProductLinkParsingDto>> readProductLinksByProduct(@PathVariable String category){
    List<ProductLinkParsingDto> result = productDatabaseSearchService.readSubProductLinksByProduct(category);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/products/")
  public ResponseEntity<List<ProductParsingDto>> readAllProducts(){
    List<ProductParsingDto> result = productDatabaseSearchService.readAllProducts();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/products/{category}")
  public ResponseEntity<List<ProductParsingDto>> readProductsByCategory(@PathVariable String category){
    List<ProductParsingDto> result = productDatabaseSearchService.readProductsByCategory(category);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/product/{productName}")
  public ResponseEntity<ProductParsingDto> readProductByName(@PathVariable String productName){
    ProductParsingDto result = productDatabaseSearchService.readProductByName(productName);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<ProductParsingDto> readProductById(@PathVariable Long productId){
    ProductParsingDto result = productDatabaseSearchService.readProductById(productId);
    return ResponseEntity.ok().body(result);
  }
}

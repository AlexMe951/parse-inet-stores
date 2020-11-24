package com.alexme951.parseinetstores.controller;

import com.alexme951.parseinetstores.service.AuchanParseStoreCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuchanParseStoreController {

  private final AuchanParseStoreCategoryService service;

  @PostMapping("/parse/auchan/cataloglinks")
  public ResponseEntity<List<String>> getAllCatalogLinks(){
    List<String> result = service.parseAllCatalogLinks();
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/parse/auchan/cataloglinks/{category}")
  public ResponseEntity<List<String>> getAllCategoryCatalogLinks(
      @PathVariable("category") String category){
    List<String> result = service.parseAllCategoryCatalogLinks(category);
    return ResponseEntity.ok().body(result);
  }
}

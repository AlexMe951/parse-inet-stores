package com.alexme951.parseinetstores.controller;

import com.alexme951.parseinetstores.service.AuchanParseStoreCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuchanParseStoreController {

  private final AuchanParseStoreCategoryService service;

  @PostMapping("/parse/auchan/categories")
  public ResponseEntity<List<String>> parseAuchanCategories(){
    List<String> result = service.parseCategories();
    return ResponseEntity.ok().body(result);
  }
}

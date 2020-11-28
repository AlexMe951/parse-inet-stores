package com.alexme951.parseinetstores.controller;

import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import com.alexme951.parseinetstores.service.AuchanParseStoreCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("parse/auchan/cataloglinks")
  public ResponseEntity<Iterable<CatalogLink>> getAllSavedCatalogLinks(){
    Iterable<CatalogLink> result = service.getAllSavedCatalogLinks();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("parse/auchan/cataloglinks/{linkUrl}")
  public ResponseEntity<Iterable<CatalogLink>> getAllByLinkUrl(@PathVariable("linkUrl") String linkUrl){
    List<CatalogLink> result = service.findAllByLinkUrl(linkUrl);
    return ResponseEntity.ok().body(result);
  }

}

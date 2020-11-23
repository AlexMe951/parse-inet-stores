package com.alexme951.parseinetstores.service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuchanParseStoreCategoryService {

  String auchanInitialUrl = "https://www.auchan.ru/catalog/ptica-myaso/";

  @SneakyThrows
  public List<String> parseCategories() {
    log.info("================================= Start parsing =================================");
    Document result = Jsoup.parse(new URL(auchanInitialUrl), 10000);
    Elements allElements = result.body().getAllElements();
    String searchQuery = "[href^=/catalog/]";
    Elements selectedHrefs = allElements.select(searchQuery);
    List<String> selectedRefsUrl = selectedHrefs
            .stream()
            .map(elm -> elm.attributes().get("href"))
            .collect(Collectors.toList());
    log.info(selectedRefsUrl.toString());
    return selectedRefsUrl;
  }

  @SneakyThrows
  public List<String> parseCategories(String subcategory) {
    log.info("================================= Start parsing =================================");
    Document result = Jsoup.parse(new URL(auchanInitialUrl), 10000);
    Elements allElements = result.body().getAllElements();
    String searchQuery = "[href^=/catalog/"+subcategory+"]";
    Elements selectedHrefs = allElements.select(searchQuery);
    List<String> selectedRefsUrl = selectedHrefs
            .stream()
            .map(elm -> elm.attributes().get("href"))
            .collect(Collectors.toList());
    log.info(selectedRefsUrl.toString());
    return selectedRefsUrl;
  }

}
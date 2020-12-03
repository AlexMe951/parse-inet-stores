package com.alexme951.parseinetstores.service;

import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_CATALOG_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_DEFAULT_CATEGORY_URL;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.ProjectConstants.HREF_HTML_ATTR_KEY;

import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuchanParseStoreCategoryService {

  private final CatalogLinkRepository repository;
  private final JsoupFacadeService jsoup;

  @SneakyThrows
  public List<String> parseAllCatalogLinks() {
    Elements allElements = jsoup.parsePage(AUCHAN_DEFAULT_CATEGORY_URL);
    Elements catalogLinkElements = allElements.select(CATALOG_LINKS_SELECT_QUERY);
    List<String> catalogLinkUrls = getUrlsFromHrefs(catalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  @SneakyThrows
  public List<String> parseAllCategoryCatalogLinks(String category) {
    String urlForParsing = formUrlForParsing(category);
    Elements allElements = jsoup.parsePage(urlForParsing);
    String selectQuery = createCategorySelectQuery(category);
    Elements categoryCatalogLinkElements = allElements.select(selectQuery);
    List<String> catalogLinkUrls = getUrlsFromHrefs(categoryCatalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  private String formUrlForParsing(String category) {
    return AUCHAN_CATALOG_LINK_PREFIX + category + "/";
  }

  private String createCategorySelectQuery(String category) {
    return String.format(CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE, category);
  }

  private List<String> getUrlsFromHrefs(Elements hrefElements) {
    return hrefElements
        .stream()
        .map(elm -> elm.attributes().get(HREF_HTML_ATTR_KEY))
        .collect(Collectors.toList());
  }

  private Set<CatalogLink> mapToCatalogLinks(List<String> catalogLinkUrls) {
    LocalDateTime parsingTime = LocalDateTime.now();
    return catalogLinkUrls.stream()
        .map(url -> new CatalogLink(null, parsingTime, url))
        .collect(Collectors.toSet());
  }

  private void saveLinks(List<String> catalogLinkUrls) {
    log.debug("Started saving links");
    Set<CatalogLink> catalogLinks = mapToCatalogLinks(catalogLinkUrls);
    repository.saveAll(catalogLinks);
    log.debug("{} links was saved into DB", catalogLinks.size());
  }

  public Iterable<CatalogLink> getAllSavedCatalogLinks() {
    return repository.findAll();
  }

  public List<CatalogLink> getAllByLinkUrl(String linkUrl) {
    return repository.findAllByLinkUrl(linkUrl);
  }

}